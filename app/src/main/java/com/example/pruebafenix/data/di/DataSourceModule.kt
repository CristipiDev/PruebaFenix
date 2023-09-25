package com.example.pruebafenix.data.di

import android.content.Context
import androidx.room.Room
import com.example.pruebafenix.data.database.DbDataSource
import com.example.pruebafenix.data.database.dao.LessonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Injectamos lo que tiene que ver con fuentes de datos:
//RETROFIT y ROOM
@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    //ROOM
    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context): DbDataSource {
        val db = Room.databaseBuilder(context, DbDataSource::class.java, "lesson_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        db.openHelper.writableDatabase
        return db
    }

    @Singleton
    @Provides
    fun lessonDao(db: DbDataSource): LessonDao = db.lessonDao()
}