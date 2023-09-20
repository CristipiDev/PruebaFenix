package com.example.pruebafenix.domain.model

class LessonsProvider {

    fun getLessonsForDay(day:Int): List<LessonModel>{
        val listLessonForDay: ArrayList<LessonModel> = ArrayList()

        lessons.forEach {lesson ->
            if (lesson.lessonDay == day) listLessonForDay.add(lesson)
        }

        return listLessonForDay
    }

    val lessons = listOf<LessonModel>(
        LessonModel("Pole Exotic", 0),
        LessonModel("Pole Sport", 0),
        LessonModel("Aro", 0),
        LessonModel("Pole Urban", 1),
        LessonModel("Pole Sport", 1),
        LessonModel("Pole Sport", 2),
        LessonModel("Pole Exotic", 2),
        LessonModel("Pole Sport", 2),
        LessonModel("Telas", 2),
        LessonModel("Flesibilidad", 3),
        LessonModel("Pole Exotic", 3),
        LessonModel("Pole Sport", 3),
        LessonModel("Aro", 4),
        LessonModel("Pole Conditioning", 4),
        LessonModel("Flesibilidad", 4)
    )
}