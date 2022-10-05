package com.example.calculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataEntity::class] , version = 1 , exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getCalculatorDao():CalculatorDao
}