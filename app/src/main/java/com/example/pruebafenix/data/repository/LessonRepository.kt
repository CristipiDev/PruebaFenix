package com.example.pruebafenix.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pruebafenix.data.database.dao.LessonDao
import com.example.pruebafenix.data.database.entity.LessonEntity
import com.example.pruebafenix.domain.model.LessonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LessonRepository {
    suspend fun setNewLessonInDb(newLesson: LessonModel)
    suspend fun getAllLessonsFromDb(): ArrayList<LessonModel>
    suspend fun getLessonFromId(lessonId: Int): LessonModel
    suspend fun deleteLessonFromId(lessonId: Int)
    suspend fun updateLesson(lesson: LessonModel)

}

class LessonRepositoryImpl @Inject constructor(
    private val lessonDao: LessonDao
): LessonRepository  {
    override suspend fun setNewLessonInDb(newLesson: LessonModel) {
        var id: Long = 0

        id = lessonDao.insertNewLesson(LessonEntity(
            newLesson.lessonColor,
            newLesson.lessonDay,
            newLesson.lessonName,
            newLesson.lessonStartTime,
            newLesson.lessonEndTime,
            newLesson.lessonVacancy,
            newLesson.id
        ))
    }

    override suspend fun getAllLessonsFromDb(): ArrayList<LessonModel> {
        val listEntity = lessonDao.getAllLessons()
        val lessonList = ArrayList<LessonModel>()

        listEntity.forEach{ lessonEntity ->
            lessonList.add(
                LessonModel(
                    lessonEntity.lessonColor,
                    lessonEntity.lessonDay,
                    lessonEntity.lessonName,
                    lessonEntity.lessonStartTime,
                    lessonEntity.lessonEndTime,
                    lessonEntity.lessonVacancy,
                    lessonEntity.id
                )
            )
        }
        return lessonList
    }

    override suspend fun getLessonFromId(lessonId: Int): LessonModel {
        val lessonEntity = lessonDao.getLesson(lessonId = lessonId)
        return LessonModel(
            lessonEntity.lessonColor,
            lessonEntity.lessonDay,
            lessonEntity.lessonName,
            lessonEntity.lessonStartTime,
            lessonEntity.lessonEndTime,
            lessonEntity.lessonVacancy,
            lessonEntity.id
        )
    }

    override suspend fun deleteLessonFromId(lessonId: Int) {
        lessonDao.deleteLesson(lessonId)
    }

    override suspend fun updateLesson(lesson: LessonModel) {
        val lessonEntity = LessonEntity(
            lesson.lessonColor,
            lesson.lessonDay,
            lesson.lessonName,
            lesson.lessonStartTime,
            lesson.lessonEndTime,
            lesson.lessonVacancy,
            lesson.id
        )
        lessonDao.updateLesson(lessonEntity)
    }


}