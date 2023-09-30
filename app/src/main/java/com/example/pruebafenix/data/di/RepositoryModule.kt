package com.example.pruebafenix.data.di

import com.example.pruebafenix.data.repository.LessonRepository
import com.example.pruebafenix.data.repository.LessonRepositoryImpl
import com.example.pruebafenix.data.repository.StudentRepository
import com.example.pruebafenix.data.repository.StudentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun lessonRepository(lessonRepository: LessonRepositoryImpl): LessonRepository

    @Binds
    abstract fun studentRepository(studentRepository: StudentRepositoryImpl): StudentRepository

}