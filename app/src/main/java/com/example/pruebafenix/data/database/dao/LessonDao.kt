package com.example.pruebafenix.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.pruebafenix.data.database.entity.LessonEntity
import com.example.pruebafenix.data.database.entity.LessonStudentCrossRefEntity
import com.example.pruebafenix.data.database.entity.LessonWithStudentsEntity
import com.example.pruebafenix.data.database.entity.StudentEntity

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewLesson(lesson: LessonEntity): Long

    @Query("SELECT * FROM lesson ORDER BY id DESC")
    fun getAllLessons(): List<LessonEntity>

    @Query("SELECT * FROM lesson WHERE id = :lessonId")
    fun getLesson(lessonId: Long): LessonEntity

    @Query("DELETE FROM lesson WHERE id = :lessonId")
    fun deleteLesson(lessonId: Long)

    @Update
    fun updateLesson(lesson: LessonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewStudent(student: StudentEntity): Long

    @Transaction
    @Query("SELECT * FROM lesson WHERE id = :lessonId")
    fun getLessonWithStudents(lessonId: Long): LessonWithStudentsEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLessonWithStudents(join: LessonStudentCrossRefEntity)

    @Query("DELETE FROM student WHERE studentId = :studentId")
    fun deleteStudent(studentId: Long)

}