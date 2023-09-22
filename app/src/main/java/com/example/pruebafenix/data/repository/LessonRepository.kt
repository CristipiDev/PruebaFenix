package com.example.pruebafenix.data.repository

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
    suspend fun getAllLessonsFromDb(): MutableLiveData<ArrayList<LessonModel>>

}

class LessonRepositoryImpl @Inject constructor(
    private val lessonDao: LessonDao
): LessonRepository  {
    override suspend fun setNewLessonInDb(newLesson: LessonModel) {
        withContext(Dispatchers.IO){
            lessonDao.insertNewLesson(LessonEntity(
                newLesson.lessonColor,
                newLesson.lessonDay,
                newLesson.lessonName,
                newLesson.lessonStartTime,
                newLesson.lessonEndTime,
                newLesson.lessonVacancy
            ))
        }
    }

    override suspend fun getAllLessonsFromDb(): MutableLiveData<ArrayList<LessonModel>> {
        val lessonMutableList = MutableLiveData<ArrayList<LessonModel>>()

        withContext(Dispatchers.IO) {
            val listEntity = lessonDao.getAllLessons().value

            lessonMutableList.value = ArrayList<LessonModel>()
            val lessonList = ArrayList<LessonModel>()

            listEntity?.let {
                it.forEach{ lessonEntity ->
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
            }
            lessonMutableList.postValue(lessonList)
        }
        return lessonMutableList
    }

}