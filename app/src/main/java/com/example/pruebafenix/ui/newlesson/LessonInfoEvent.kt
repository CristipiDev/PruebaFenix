package com.example.pruebafenix.ui.newlesson

sealed class LessonInfoEvent {

    class OnChangeColor(val color: Int): LessonInfoEvent()
    class OnChangeDayName(val dayName: String): LessonInfoEvent()
    class OnChangeName(val name: String): LessonInfoEvent()
    class OnChangeStartTime(val startTime: String): LessonInfoEvent()
    class OnChangeEndTime(val endTime: String): LessonInfoEvent()
    class OnChangeVacancy(val vacancy: Int): LessonInfoEvent()

    class OnClickDropdown(val expanded: Boolean): LessonInfoEvent()
}