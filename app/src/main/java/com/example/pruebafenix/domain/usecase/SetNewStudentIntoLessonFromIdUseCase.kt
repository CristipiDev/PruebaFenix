package com.example.pruebafenix.domain.usecase

import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.data.repository.StudentRepository
import com.example.pruebafenix.domain.model.LessonWithStudentsModel
import javax.inject.Inject

class SetNewStudentIntoLessonFromIdUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {
    private var lessonId: Long = -1
    private var studentId: Long = -1
    suspend operator fun invoke() {
        studentRepository.setNewStudentIntoLessonFromId(lessonId, studentId)
    }

    fun setLessonId(lessonId: Long) { this.lessonId = lessonId }
    fun setStudentId(studentId: Long) { this.studentId = studentId }
}