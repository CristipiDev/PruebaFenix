package com.example.pruebafenix.data.repository

import com.example.pruebafenix.data.database.dao.LessonDao
import com.example.pruebafenix.data.database.entity.LessonStudentCrossRefEntity
import com.example.pruebafenix.data.database.entity.StudentEntity
import com.example.pruebafenix.domain.model.StudentModel
import javax.inject.Inject

interface StudentRepository {

    suspend fun setNewStudent(newStudent: StudentModel): Long

    suspend fun setNewStudentIntoLessonFromId(lessonId: Long, studentId: Long)

    suspend fun deleteStudentFromId(studentId: Long)
}
class StudentRepositoryImpl @Inject constructor(
    private val lessonDao: LessonDao
): StudentRepository  {
    override suspend fun setNewStudent(newStudent: StudentModel): Long {
        var id: Long = -1
        val studentEntity = StudentEntity(newStudent.studentName)

        id = lessonDao.insertNewStudent(studentEntity)

        return id
    }

    override suspend fun setNewStudentIntoLessonFromId(lessonId: Long, studentId: Long) {
        val lessonStudentCrossRefEntity = LessonStudentCrossRefEntity(lessonId, studentId)

        lessonDao.insertLessonWithStudents(lessonStudentCrossRefEntity)
    }

    override suspend fun deleteStudentFromId(studentId: Long) {
        lessonDao.deleteStudent(studentId)
    }
}