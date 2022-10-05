package com.example.calculator.di

import android.app.Application
import androidx.room.Room
import com.example.calculator.util.Constants.Companion.DB_NAME
import com.example.calculator.data.db.AppDatabase
import com.example.calculator.data.db.CalculatorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideArticleDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesArticlesDao(articleDatabase: AppDatabase): CalculatorDao {
        return articleDatabase.getCalculatorDao()
    }
}