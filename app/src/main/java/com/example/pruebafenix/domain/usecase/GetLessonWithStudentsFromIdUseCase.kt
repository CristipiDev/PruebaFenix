package com.example.pruebafenix.domain.usecase

import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.domain.model.LessonWithStudentsModel
import javax.inject.Inject

class GetLessonWithStudentsFromIdUseCase @Inject constructor(
    private val lessonRepository: LessonRepository
) {
    private var lessonId: Int = 0
    suspend operator fun invoke(): LessonWithStudentsModel {
        return lessonRepository.getLessonWithStudentsFromId(lessonId = lessonId)
    }

    fun setLessonId(lessonId: Int) { this.lessonId = lessonId }
}