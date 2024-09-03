package com.example.chetutodo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NodesDao {

    @Insert
    suspend fun insertUser(vararg notes: Notes)

    @Query("select * from notes")
    fun getAllUser():LiveData<List<Notes>>
    @Delete
    suspend fun deleteUser(notes: Notes)
    @Update
    suspend fun updateUser(notes: Notes)

    @Query("delete from notes")

    suspend fun deleteAllUser()





}