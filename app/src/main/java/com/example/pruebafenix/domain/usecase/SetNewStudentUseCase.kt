package com.example.pruebafenix.domain.usecase

import com.example.pruebafenix.data.repository.StudentRepository
import com.example.pruebafenix.domain.model.StudentModel
import javax.inject.Inject

class SetNewStudentUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) {
    private lateinit var student: StudentModel
    suspend operator fun invoke(): Long {
        return studentRepository.setNewStudent(student)
    }

    fun setStudent(student: StudentModel) { this.student = student }
}