package com.example.aplikasisiswa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Siswa::class], version=1, exportSchema = false)
abstract class DatabaseSiswa :RoomDatabase(){
    abstract fun siswaDao():siswaDao

    companion object{
        @Volatile
        private var Instance:DatabaseSiswa?=null

        fun getDataabse(context: Context):DatabaseSiswa{
            return (Instance?: synchronized(this){
                Room.databaseBuilder(context,
                    DatabaseSiswa::class.java,
                "siswa_database").build().also { Instance=it}
            })
        }
    }
}