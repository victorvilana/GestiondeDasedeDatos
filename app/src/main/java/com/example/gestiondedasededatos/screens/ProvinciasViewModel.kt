package com.example.gestiondedasededatos.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiondedasededatos.models.Provincia
import com.example.gestiondedasededatos.repository.ProvinciasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProvinciasViewModel @Inject constructor(
    private val repositorioProvincias: ProvinciasRepository
) : ViewModel() {

    private val _provinciasList = MutableStateFlow<List<Provincia>>(emptyList())
    val provinciasList = _provinciasList.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repositorioProvincias.getAllProvincias().distinctUntilChanged()
                .collect() { lista ->
                    if (lista.isNullOrEmpty()) {
                        Log.d("Provincias", "Listado vacíode provincias")
                    } else {
                        _provinciasList.value = lista
                    }
                }

        }
    }

    fun insertProvincias(provincia: Provincia) = viewModelScope.launch {
        try {
            _error.value = null
            repositorioProvincias.insertProvincias(provincia)
        } catch (e: Exception) {
            val message = e.message ?: ""
            if (message.contains("PRIMARY KEY", ignoreCase = true) || message.contains("UNIQUE", ignoreCase = true)) {
                _error.value = "Error: El código de provincia '${provincia.cod_provincia}' ya está registrado."
            } else {
                _error.value = "Error al guardar la provincia: ${e.message}"
            }
            Log.e("ProvinciasViewModel", "Error al insertar: ${e.message}")
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun updateProvincias (provincia : Provincia) = viewModelScope.launch {
        repositorioProvincias.updateProvincias(provincia)
    }

    fun deleteProvincias (provincia : Provincia) = viewModelScope.launch {
        repositorioProvincias.deleteProvincias(provincia)
    }

    fun deleteAllProvincias () = viewModelScope.launch {
        repositorioProvincias.deleteAllProvincias()
    }

    fun getAllProvincias () = viewModelScope.launch {
        repositorioProvincias.getAllProvincias()
    }

    fun getProvinciasByID (provincia : Provincia) = viewModelScope.launch {
        repositorioProvincias.getProvinciasById((provincia.cod_provincia))
    }

    fun getProvinciasByNombre (provincia : Provincia) = viewModelScope.launch {
        repositorioProvincias.getProvinciasByNombre((provincia.nombre_provincia))
    }

}