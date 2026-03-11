package com.example.gestiondedasededatos.repository

import com.example.gestiondedasededatos.data.ProvinciasDBDao
import com.example.gestiondedasededatos.models.Provincia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProvinciasRepository @Inject constructor(
    private val provinciasDao: ProvinciasDBDao
) {
    suspend fun insertProvincias (provincia: Provincia) = provinciasDao.insertProvincias(provincia)
    suspend fun updateProvincias (provincia: Provincia) = provinciasDao.updateProvincias(provincia)
    suspend fun deleteProvincias (provincia: Provincia) = provinciasDao.deleteProvincias(provincia)
    suspend fun deleteAllProvincias () = provinciasDao.deleteAllProvincias()

    fun getAllProvincias(): Flow<List<Provincia>>
    = provinciasDao.getAllProvincias().flowOn(Dispatchers.IO).conflate()

    suspend fun getProvinciasById(id: String): Provincia = provinciasDao.getProvinciasById(id)
    suspend fun getProvinciasByNombre(id: String): Provincia = provinciasDao.getProvinciasByNombre(id)

}