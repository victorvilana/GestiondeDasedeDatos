package com.example.gestiondedasededatos.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gestiondedasededatos.models.Canton

import kotlinx.coroutines.flow.Flow


@Dao
interface CantonesDBDao {

    @Query("SELECT * from t_cantones")
    fun getAllCantones(): Flow<List<Canton>>

    @Query("SELECT * from t_cantones where cod_canton = :id")
    suspend fun getCantonesById(id: String): Canton

    @Query("SELECT * from t_cantones where nombre_canton = :id")
    suspend fun getCantonesByNombre(id: String): Canton

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCantones(canton: Canton)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCantones(canton: Canton)

    @Delete
    suspend fun deleteCantones(canton: Canton)

    @Query("DELETE from t_cantones")
    suspend fun deleteAllCantones()

}