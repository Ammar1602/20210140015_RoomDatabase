package com.example.aplikasisiswa.navigasi

interface DestinasiNavigasi {
    /** nama naik untuk menentukan jalur untuk composable **/
    val route: String

    /** string resource id yang berisi judul yang akan ditampilkan di layar halaman.**/
    val titileRes: Int
}