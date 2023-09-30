package com.example.pruebafenix.domain.usecase

import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.domain.model.LessonModel
import javax.inject.Inject

class DeleteLessonFromIdUseCase @Inject constructor(
    private val lessonRepository: LessonRepository
) {
    private var lessonId: Long = -1
    suspend operator fun invoke() {
        lessonRepository.deleteLessonFromId(lessonId)
    }

    fun setLessonId(lessonId: Long) { this.lessonId = lessonId }
}