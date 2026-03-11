package com.example.gestiondedasededatos.repository

import com.example.gestiondedasededatos.data.CantonesDBDao
import com.example.gestiondedasededatos.data.ProvinciasDBDao
import com.example.gestiondedasededatos.models.Canton
import com.example.gestiondedasededatos.models.Provincia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CantonesRepository @Inject constructor(
    private val cantonesDao: CantonesDBDao
) {
    suspend fun insertCantones (canton: Canton) = cantonesDao.insertCantones(canton)
    suspend fun updateCantones (canton: Canton) = cantonesDao.updateCantones(canton)
    suspend fun deleteCantones (canton: Canton) = cantonesDao.deleteCantones(canton)
    suspend fun deleteAllCantones () = cantonesDao.deleteAllCantones()

    fun getAllCantones(): Flow<List<Canton>>
    = cantonesDao.getAllCantones().flowOn(Dispatchers.IO).conflate()

    suspend fun getCantonesById(id: String): Canton = cantonesDao.getCantonesById(id)
    suspend fun getCantonesByNombre(id: String): Canton = cantonesDao.getCantonesByNombre(id)

}