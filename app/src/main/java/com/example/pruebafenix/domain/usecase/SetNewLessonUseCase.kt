package com.example.pruebafenix.domain.usecase

import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.domain.model.LessonModel
import javax.inject.Inject

class SetNewLessonUseCase @Inject constructor(
    private val lessonRepository: LessonRepository
) {

    private lateinit var newLesson: LessonModel

    suspend operator fun invoke() {
        lessonRepository.setNewLessonInDb(newLesson)
    }

    fun lessonToAdd(lesson: LessonModel) { this.newLesson = lesson }

}