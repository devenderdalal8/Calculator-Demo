package com.example.calculator.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.calculator.util.Constants.Companion.TABLE_NAME

@Dao
interface CalculatorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dataEntity: DataEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllData(): List<DataEntity>
}