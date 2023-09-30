package com.example.pruebafenix.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson")
data class LessonEntity(
    @ColumnInfo(name = "lessonColor") val lessonColor: Int,
    @ColumnInfo(name = "lessonDay") val lessonDay: String,
    @ColumnInfo(name = "lessonName") val lessonName: String,
    @ColumnInfo(name = "lessonStartTime") val lessonStartTime: String,
    @ColumnInfo(name = "lessonEndTime") val lessonEndTime: String,
    @ColumnInfo(name = "lessonVacancy") val lessonVacancy: Int,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)