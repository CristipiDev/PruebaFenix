package com.example.pruebafenix.domain.usecase

import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.data.repository.StudentRepository
import javax.inject.Inject

class DeleteStudentFromIdUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {
    private var studentId: Long = -1
    suspend operator fun invoke() {
        studentRepository.deleteStudentFromId(studentId)
    }

    fun setStudentId(studentId: Long) { this.studentId = studentId }
}