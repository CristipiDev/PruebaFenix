package com.example.pruebafenix.domain.usecase

import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.domain.model.LessonModel
import javax.inject.Inject

class UpdateLessonUseCase @Inject constructor(
    private val lessonRepository: LessonRepository
) {
    private lateinit var lesson: LessonModel
    suspend operator fun invoke() {
        lessonRepository.updateLesson(lesson)
    }

    fun setLesson(lesson: LessonModel) { this.lesson = lesson }
}