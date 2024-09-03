package com.example.chetutodo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val Id:Int,
    @ColumnInfo(name="title")
    val Title:String,
    @ColumnInfo(name = "discrition")
    val Driscription:String

)
