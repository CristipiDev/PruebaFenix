package com.example.pruebafenix.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.domain.model.LessonModel
import javax.inject.Inject

class GetDayLessonsUseCase @Inject constructor(
    private val lessonRepository: LessonRepository
) {

    private lateinit var dayName: String
    suspend operator fun invoke(): LiveData<ArrayList<LessonModel>>{
        val listAllLessons = lessonRepository.getAllLessonsFromDb()
        val listMutableDayLessons = MutableLiveData<ArrayList<LessonModel>>()
        var listDayLesons = ArrayList<LessonModel>()

        listAllLessons.value?.let {
            listDayLesons = getLessonsForDay(it)
        }

        listMutableDayLessons.postValue(listDayLesons)

        return listMutableDayLessons
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