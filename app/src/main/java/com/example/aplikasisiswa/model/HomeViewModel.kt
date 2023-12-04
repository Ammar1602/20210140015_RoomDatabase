package com.example.aplikasisiswa.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasisiswa.data.Siswa
import com.example.aplikasisiswa.repositori.RepositoriSiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

