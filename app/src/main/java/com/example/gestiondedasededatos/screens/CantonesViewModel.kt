package com.example.gestiondedasededatos.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiondedasededatos.models.Canton
import com.example.gestiondedasededatos.models.Provincia
import com.example.gestiondedasededatos.repository.CantonesRepository
import com.example.gestiondedasededatos.repository.ProvinciasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CantonesViewModel @Inject constructor(
    private val repositorioCantones: CantonesRepository
) : ViewModel() {

    private val _cantonesList = MutableStateFlow<List<Canton>>(emptyList())
    val cantonesList = _cantonesList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repositorioCantones.getAllCantones().distinctUntilChanged()
                .collect() { lista ->
                    if (lista.isNullOrEmpty()) {
                        Log.d("Provincias", "Listado vacíode provincias")
                    } else {
                        _cantonesList.value = lista
                    }
                }

        }
    }

    fun insertCantones (canton : Canton) = viewModelScope.launch {
        repositorioCantones.insertCantones(canton)
    }

    fun updateCantones (canton: Canton) = viewModelScope.launch {
        repositorioCantones.updateCantones(canton)
    }

    fun deleteCantones (canton : Canton) = viewModelScope.launch {
        repositorioCantones.deleteCantones(canton)
    }

    fun deleteAllCantones () = viewModelScope.launch {
        repositorioCantones.deleteAllCantones()
    }

    fun getAllCantones () = viewModelScope.launch {
        repositorioCantones.getAllCantones()
    }

    fun getCantonesByID (provincia : Provincia) = viewModelScope.launch {
        repositorioCantones.getCantonesById((provincia.cod_provincia))
    }

    fun getCantonesByNombre (provincia : Provincia) = viewModelScope.launch {
        repositorioCantones.getCantonesByNombre((provincia.nombre_provincia))
    }

}