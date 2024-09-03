package com.example.chetutodo

import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase
@Database(entities = [Notes::class], version = 1)
abstract class NotesDataBase:RoomDatabase() {
    abstract val nodesDao:NodesDao
    companion object{

        @Volatile
        private var INSTANCE:NotesDataBase?=null

        fun getInstance(context: Context):NotesDataBase{
            var instance  = INSTANCE
            synchronized(this){
                if (INSTANCE==null){
                    instance = Room.databaseBuilder(context,NotesDataBase::class.java,"ChetuT").build()
                    INSTANCE = instance

                }

            }
            return instance!!


        }





    }
}