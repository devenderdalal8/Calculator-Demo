package com.example.calculator.di

import com.example.calculator.data.DataRepositoryImpl
import com.example.calculator.data.Repository
import com.example.calculator.data.db.CalculatorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        calculatorDao: CalculatorDao
    ): Repository {
        return DataRepositoryImpl(calculatorDao)
    }
}