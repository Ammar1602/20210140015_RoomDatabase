package com.example.aplikasisiswa.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasisiswa.repositori.RepositoriSiswa
import com.example.aplikasisiswa.uii.DetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    private val siswaId: Int = checkNotNull(savedStateHandle[DetailsDestination.siswaIdArg])
    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriSiswa.getSiswaStream(siswaId)
            .filterNotNull()
            .map {
            ItemDetailsUiState(detailSiswa = it.toDetailSiswa())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailsUiState()
        )

    suspend fun deleteItem(){
        repositoriSiswa.deleteSiswa(uiState.value.detailSiswa.toSiswa())
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class  ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailSiswa: DetailSiswa = DetailSiswa(),
)

