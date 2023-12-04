package com.example.aplikasisiswa.model

import android.text.Spannable.Factory
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object PenyediaViewModel{
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }
    }
}
/** fungsi ekstensi query untuk object [Application] dan mengembalikan sebuah instence dari [AplikasiSiswa].**/