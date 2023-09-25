package com.example.pruebafenix.domain.usecase

import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.domain.model.LessonModel
import javax.inject.Inject
import kotlin.properties.Delegates

class GetLessonFromIdUseCase @Inject constructor(
    private val lessonRepository: LessonRepository
) {
    private var lessonId: Int = 0
    suspend operator fun invoke(): LessonModel{
        return lessonRepository.getLessonFromId(lessonId = lessonId)
    }

    fun setLessonId(lessonId: Int) { this.lessonId = lessonId }
}