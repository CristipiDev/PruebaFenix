package com.example.pruebafenix.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebafenix.data.database.entity.LessonEntity

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewLesson(lesson: LessonEntity)

    @Query("SELECT * FROM lesson ORDER BY id DESC")
    fun getAllLessons(): LiveData<List<LessonEntity>>

    @Query("DELETE FROM LESSON WHERE id = :lessonId")
    fun deleteLesson(lessonId: Int)
}