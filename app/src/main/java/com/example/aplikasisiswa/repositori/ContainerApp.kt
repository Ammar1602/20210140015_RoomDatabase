package com.example.aplikasisiswa.repositori

import android.content.Context
import com.example.aplikasisiswa.data.DatabaseSiswa

interface ContainerApp {
    val repositoriSiswa : RepositoriSiswa
}

class ContainerDataApp(private val context: Context): ContainerApp {
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(DatabaseSiswa.getDatabase(context).siswaDao())
    }
}