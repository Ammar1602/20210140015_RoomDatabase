package com.example.aplikasisiswa

import android.app.Application
import com.example.aplikasisiswa.repositori.ContainerApp
import com.example.aplikasisiswa.repositori.ContainerDataApp

class AplikasiSiswa : Application() {
    /** appconteiner instance digunakan oleh kelas-kelas lainnya untuk mendapatkan dependensi**/
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}