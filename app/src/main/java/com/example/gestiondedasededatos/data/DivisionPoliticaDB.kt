package com.example.gestiondedasededatos.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gestiondedasededatos.models.Canton
import com.example.gestiondedasededatos.models.Provincia

@Database(entities = [Provincia::class, Canton::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DivisionPoliticaDB : RoomDatabase() {
    abstract fun provinciasDao(): ProvinciasDBDao
    abstract fun cantonesDao(): CantonesDBDao
}
