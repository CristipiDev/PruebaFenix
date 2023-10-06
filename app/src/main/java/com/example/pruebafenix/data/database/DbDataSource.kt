package com.example.pruebafenix.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebafenix.data.database.dao.LessonDao
import com.example.pruebafenix.data.database.entity.LessonEntity
import com.example.pruebafenix.data.database.entity.LessonStudentCrossRefEntity
import com.example.pruebafenix.data.database.entity.StudentEntity

@Database(entities = [LessonEntity::class, StudentEntity::class,
    LessonStudentCrossRefEntity::class], version = 3)
abstract class DbDataSource: RoomDatabase() {

    abstract fun lessonDao(): LessonDao
}