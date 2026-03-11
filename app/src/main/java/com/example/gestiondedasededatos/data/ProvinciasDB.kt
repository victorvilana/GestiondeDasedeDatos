package com.example.gestiondedasededatos.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gestiondedasededatos.models.Provincia

@Database (entities = [Provincia::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)

abstract class ProvinciasDB : RoomDatabase() {
    abstract fun provinciasDao(): ProvinciasDBDao
}
