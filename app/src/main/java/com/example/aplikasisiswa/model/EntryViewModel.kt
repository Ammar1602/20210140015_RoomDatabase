package com.example.aplikasisiswa.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aplikasisiswa.data.Siswa
import com.example.aplikasisiswa.repositori.RepositoriSiswa

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel(){

    /*** Berisi Status Siswa Saat Ini ***/
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /** fungsi untuk memvalidasi input **/
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean{
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa){
        uiStateSiswa = UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    /** fungsi untuk menyimpan data yang di-entry**/
    suspend fun saveSiswa(){
        if (validasiInput()){
            repositoriSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}
/**mewakili State Ui untuk siswa**/
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = "",
)
/**fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya**/
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telepon = telpon
)

fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)

fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telepon
)
