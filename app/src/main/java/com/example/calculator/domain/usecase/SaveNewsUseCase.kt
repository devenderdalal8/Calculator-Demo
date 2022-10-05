package com.example.calculator.domain.usecase

import com.example.calculator.data.db.CalculatorDao
import com.example.calculator.data.db.DataEntity
import javax.inject.Inject

class SaveDataUseCase @Inject constructor( val calculatorDao: CalculatorDao) {
    suspend fun execute(data: DataEntity) {
        calculatorDao.insert(data)
    }
}