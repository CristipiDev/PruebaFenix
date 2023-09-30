package com.example.pruebafenix.data.repository

import com.example.pruebafenix.data.database.dao.LessonDao
import com.example.pruebafenix.data.database.entity.LessonEntity
import com.example.pruebafenix.data.database.entity.LessonWithStudentsEntity
import com.example.pruebafenix.domain.model.LessonModel
import com.example.pruebafenix.domain.model.LessonWithStudentsModel
import com.example.pruebafenix.domain.model.StudentModel
import javax.inject.Inject

interface LessonRepository {
    suspend fun setNewLessonInDb(newLesson: LessonModel)
    suspend fun getAllLessonsFromDb(): ArrayList<LessonModel>
    suspend fun getLessonFromId(lessonId: Int): LessonModel
    suspend fun deleteLessonFromId(lessonId: Int)
    suspend fun updateLesson(lesson: LessonModel)

    suspend fun getLessonWithStudentsFromId(lessonId: Long): LessonWithStudentsModel

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

    override suspend fun getLessonWithStudentsFromId(lessonId: Long): LessonWithStudentsModel {
        val lessonWithStudents = lessonDao.getLessonWithStudents(lessonId)

        val lesson = LessonModel(
            lessonWithStudents.lesson.lessonColor,
            lessonWithStudents.lesson.lessonDay,
            lessonWithStudents.lesson.lessonName,
            lessonWithStudents.lesson.lessonStartTime,
            lessonWithStudents.lesson.lessonEndTime,
            lessonWithStudents.lesson.lessonVacancy,
            lessonWithStudents.lesson.id
        )

        val studentList: ArrayList<StudentModel> = ArrayList()
        lessonWithStudents.students.forEach {studentEntity ->
            studentList.add(StudentModel(
                studentName = studentEntity.studentName,
                studentId = studentEntity.studentId
            ))
        }

        return LessonWithStudentsModel(
            lesson = lesson,
            studentList = studentList
        )
    }


}