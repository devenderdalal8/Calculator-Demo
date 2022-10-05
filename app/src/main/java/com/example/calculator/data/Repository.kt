package com.example.calculator.data

import com.example.calculator.data.db.DataEntity

interface Repository {
    suspend fun getAllQuery(): List<DataEntity>
    suspend fun insert(dataEntity: DataEntity)
}