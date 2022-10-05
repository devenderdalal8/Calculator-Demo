package com.example.calculator.domain.usecase

import com.example.calculator.data.db.CalculatorDao
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(private val calculatorDao: CalculatorDao) {
//    fun execute(): Flow<List<ApiResponse.Article>> {
//        return newsRepository.getSavedNews()
//    }
}