package com.example.calculator.data

import com.example.calculator.data.db.CalculatorDao
import com.example.calculator.data.db.DataEntity
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val calculatorDao: CalculatorDao
):Repository {
    override suspend fun getAllQuery() = calculatorDao.getAllData()
    override suspend fun insert(dataEntity: DataEntity) {
        calculatorDao.insert(dataEntity)
    }

}