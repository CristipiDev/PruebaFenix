package com.example.pruebafenix.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.pruebafenix.data.database.entity.LessonEntity
import com.example.pruebafenix.data.database.entity.LessonWithStudentsEntity

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewLesson(lesson: LessonEntity): Long

    @Query("SELECT * FROM lesson ORDER BY id DESC")
    fun getAllLessons(): List<LessonEntity>

    @Query("SELECT * FROM LESSON WHERE id = :lessonId")
    fun getLesson(lessonId: Int): LessonEntity

    @Query("DELETE FROM LESSON WHERE id = :lessonId")
    fun deleteLesson(lessonId: Int)

    @Update
    fun updateLesson(lesson: LessonEntity)

    @Transaction
    @Query("SELECT * FROM lesson WHERE id = :lessonId")
    fun getLessonWithStudents(lessonId: Int): LessonWithStudentsEntity

}