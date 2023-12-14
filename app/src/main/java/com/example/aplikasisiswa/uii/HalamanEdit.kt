package com.example.aplikasisiswa.uii

import SiswaTopAppBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasisiswa.R
import com.example.aplikasisiswa.model.EditViewModel
import com.example.aplikasisiswa.model.PenyediaViewModel
import com.example.aplikasisiswa.navigasi.DestinasiNavigasi
import kotlinx.coroutines.launch

object ItemEditDestination : DestinasiNavigasi{
    override val route = "item_edit"
    override val titileRes = R.string.edit
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditSecreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(ItemEditDestination.titileRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp)
        },
        modifier = modifier
    ) {innerPadding ->
        EntrySiswaBody(
            uiStateSiswa = viewModel.siswaUiState,
            onSiwaValueChange = viewModel::updateUiState,
            onSaveClick = {
                // Note: if the user rotates the screen very fast, the operation may get cancelled
                // and the item may not updated in Database. This is because when config
                // change occurs, the Activity wil lbe recreated and the rememberCoroutineScope will
                // be cancelled - since the scope is bound to composition
                coroutineScope.launch {
                    viewModel.updateSiswa()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}