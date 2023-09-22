package com.example.pruebafenix.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebafenix.data.database.dao.LessonDao
import com.example.pruebafenix.data.database.entity.LessonEntity

@Database(entities = [LessonEntity::class], version = 1)
abstract class DbDataSource: RoomDatabase() {

    abstract fun lessonDao(): LessonDao
}