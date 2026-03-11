package com.example.gestiondedasededatos.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import kotlin.time.ExperimentalTime


@Entity (tableName = "t_provincias")
data class Provincia @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey
    val cod_provincia : String,
    val nombre_provincia : String,
    val fecha : Date = Date.from(Instant.now())

)
