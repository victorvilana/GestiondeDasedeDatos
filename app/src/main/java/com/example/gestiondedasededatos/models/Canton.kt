package com.example.gestiondedasededatos.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import kotlin.time.ExperimentalTime
import com.example.gestiondedasededatos.models.Provincia


@Entity(
    tableName = "t_cantones",
    foreignKeys = [
        ForeignKey(
            entity = Provincia::class,
            parentColumns = ["cod_provincia"],
            childColumns = ["cod_provincia"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["cod_provincia"])]
)

data class Canton @OptIn(ExperimentalTime::class) constructor(

    val cod_provincia : String,
    @PrimaryKey
    val cod_canton : String,
    val nombre_canton : String,
    val fecha : Date = Date.from(Instant.now())

)
