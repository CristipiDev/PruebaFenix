package com.example.pruebafenix.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.domain.model.LessonModel
import javax.inject.Inject

class GetDayLessonListUseCase @Inject constructor(
    private val lessonRepository: LessonRepository
) {

    private lateinit var dayName: String
    suspend operator fun invoke(): ArrayList<LessonModel>{
        val listAllLessons = lessonRepository.getAllLessonsFromDb()

        return getLessonsForDay(listAllLessons)
    }

    fun setDayName(dayName: String) { this.dayName = dayName }

    private fun getLessonsForDay(lessons: ArrayList<LessonModel>): ArrayList<LessonModel>{
        val listLessonForDay: ArrayList<LessonModel> = ArrayList()

        lessons.forEach {lesson ->
            if (lesson.lessonDay == dayName) listLessonForDay.add(lesson)
        }

        return listLessonForDay
    }
}