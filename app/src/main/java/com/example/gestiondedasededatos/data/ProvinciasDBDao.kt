package com.example.gestiondedasededatos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gestiondedasededatos.models.Provincia
import kotlinx.coroutines.flow.Flow


@Dao
interface ProvinciasDBDao {

    @Query("SELECT * from t_provincias ORDER BY cod_provincia ASC")
    fun getAllProvincias(): Flow<List<Provincia>>

    @Query("SELECT * from t_provincias where cod_provincia = :id")
    suspend fun getProvinciasById(id: String): Provincia

    @Query("SELECT * from t_provincias where nombre_provincia = :id")
    suspend fun getProvinciasByNombre(id: String): Provincia

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProvincias(provincia: Provincia)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProvincias(provincia: Provincia)

    @Delete
    suspend fun deleteProvincias(provincia: Provincia)

    @Query("DELETE from t_provincias")
    suspend fun deleteAllProvincias()

}