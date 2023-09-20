package com.example.pruebafenix.domain.model

import com.example.pruebafenix.R

class LessonsProvider {

    fun getLessonsForDay(day:Int): List<LessonModel>{
        val listLessonForDay: ArrayList<LessonModel> = ArrayList()

        lessons.forEach {lesson ->
            if (lesson.lessonDay == day) listLessonForDay.add(lesson)
        }

        return listLessonForDay
    }

    val lessons = listOf<LessonModel>(
        LessonModel(R.color.salmon, 0, "Pole Exotic", "11:30", "13:00", 10),
        LessonModel(R.color.blue, 0, "Pole Sport", "17:30", "19:00", 10),
        LessonModel(R.color.caramel, 0, "Aro", "19:30", "21:00", 10),
        LessonModel(R.color.pink, 1,"Pole Urban", "18:00", "19:00", 10),
        LessonModel(R.color.blue, 1, "Pole Sport", "19:00", "20:30", 10),
        LessonModel(R.color.blue, 2, "Pole Sport", "10:00", "11:30", 10),
        LessonModel(R.color.salmon, 2, "Pole Exotic", "11:30", "13:00", 10),
        LessonModel(R.color.blue, 2, "Pole Sport", "17:30", "19:00", 10),
        LessonModel(R.color.orange, 2,"Telas", "19:30", "21:00", 10),
        LessonModel(R.color.grey, 3,"Flesibilidad", "10:00", "11:00", 10),
        LessonModel(R.color.lilac, 3, "Pole Exotic", "18:00", "19:00", 10),
        LessonModel(R.color.blue, 3, "Pole Sport", "19:00", "20:30", 10),
        LessonModel(R.color.caramel, 4, "Aro", "10:00", "11:30", 10),
        LessonModel(R.color.green, 4, "Pole Conditioning", "18:00", "19:00", 10),
        LessonModel(R.color.grey, 4, "Flesibilidad", "19:00", "20:00", 10)
    )
}