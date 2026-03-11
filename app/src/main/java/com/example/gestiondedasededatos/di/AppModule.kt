package com.example.gestiondedasededatos.di

import android.content.Context
import androidx.room.Room
import com.example.gestiondedasededatos.data.CantonesDBDao
import com.example.gestiondedasededatos.data.DivisionPoliticaDB
import com.example.gestiondedasededatos.data.ProvinciasDBDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesDivisionPoliticaDB(@ApplicationContext context: Context): DivisionPoliticaDB =
        Room.databaseBuilder(
            context,
            DivisionPoliticaDB::class.java,
            "divisionpolitica_db"
        )
            .fallbackToDestructiveMigration()


            .build()

    @Singleton
    @Provides
    fun providesProvinciasDao(db: DivisionPoliticaDB): ProvinciasDBDao =
        db.provinciasDao()

    @Singleton
    @Provides
    fun providesCantonesDao(db: DivisionPoliticaDB): CantonesDBDao =
        db.cantonesDao()
}
