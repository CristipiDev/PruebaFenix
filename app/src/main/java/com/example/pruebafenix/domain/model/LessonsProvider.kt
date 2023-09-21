package com.example.pruebafenix.domain.model

import com.example.pruebafenix.R

class LessonsProvider {

    fun getLessonsForDay(dayName: String): List<LessonModel>{
        val listLessonForDay: ArrayList<LessonModel> = ArrayList()

        lessons.forEach {lesson ->
            if (lesson.lessonDay == dayName) listLessonForDay.add(lesson)
        }

        return listLessonForDay
    }

    val lessons = listOf<LessonModel>(
        LessonModel(R.color.salmon, "LUNES", "Pole Exotic", "11:30", "13:00", 10),
        LessonModel(R.color.blue, "LUNES", "Pole Sport", "17:30", "19:00", 10),
        LessonModel(R.color.caramel, "LUNES", "Aro", "19:30", "21:00", 10),
        LessonModel(R.color.pink, "MARTES","Pole Urban", "18:00", "19:00", 10),
        LessonModel(R.color.blue, "MARTES", "Pole Sport", "19:00", "20:30", 10),
        LessonModel(R.color.blue, "MIÉRCOLES", "Pole Sport", "10:00", "11:30", 10),
        LessonModel(R.color.salmon, "MIÉRCOLES", "Pole Exotic", "11:30", "13:00", 10),
        LessonModel(R.color.blue, "MIÉRCOLES", "Pole Sport", "17:30", "19:00", 10),
        LessonModel(R.color.orange, "MIÉRCOLES","Telas", "19:30", "21:00", 10),
        LessonModel(R.color.grey, "JUEVES","Flesibilidad", "10:00", "11:00", 10),
        LessonModel(R.color.lilac, "JUEVES", "Pole Exotic", "18:00", "19:00", 10),
        LessonModel(R.color.blue, "JUEVES", "Pole Sport", "19:00", "20:30", 10),
        LessonModel(R.color.caramel, "VIERNES", "Aro", "10:00", "11:30", 10),
        LessonModel(R.color.green, "VIERNES", "Pole Conditioning", "18:00", "19:00", 10),
        LessonModel(R.color.grey, "VIERNES", "Flesibilidad", "19:00", "20:00", 10)
    )
}