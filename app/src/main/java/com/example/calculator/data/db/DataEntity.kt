package com.example.calculator.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.calculator.util.Constants.Companion.TABLE_NAME

@Entity(tableName = "$TABLE_NAME")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int=0,
    @ColumnInfo(name = "query") val query: String,
    @ColumnInfo(name = "value") val value: String,

    )