package com.example.pruebafenix.domain.usecase

import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.domain.model.LessonModel
import com.example.pruebafenix.ui.utils.CheckDataUtils
import javax.inject.Inject

class GetDayLessonListUseCase @Inject constructor(
    private val lessonRepository: LessonRepository
) {

    private lateinit var dayName: String
    suspend operator fun invoke(): ArrayList<LessonModel>{
        val allLessonList = lessonRepository.getAllLessonsFromDb()
        val dayLessonList = orderDayLessonList(allLessonList)

        return getDayLessonList(dayLessonList)
    }

    fun setDayName(dayName: String) { this.dayName = dayName }

    private fun getDayLessonList(lessons: List<LessonModel>): ArrayList<LessonModel>{
        val listLessonForDay: ArrayList<LessonModel> = ArrayList()

        lessons.forEach {lesson ->
            if (lesson.lessonDay == dayName) listLessonForDay.add(lesson)
        }

        return listLessonForDay
    }

    private fun orderDayLessonList(dayLessons: List<LessonModel>): List<LessonModel> {
        var orderLessons: List<LessonModel> = ArrayList()

        if (!dayLessons.isNullOrEmpty()) {
            orderLessons = dayLessons.sortedBy {
                CheckDataUtils.splitTime(it.lessonStartTime)[0]
            }
        }

        return orderLessons
    }
}