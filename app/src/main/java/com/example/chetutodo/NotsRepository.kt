package com.example.chetutodo

class NotsRepository(private val notesDao: NodesDao) {

    suspend fun saveUser(notes: Notes) {
        notesDao.insertUser(notes)

    }

    val notes = notesDao.getAllUser()

    suspend fun deleteAllUser() {
        notesDao.deleteAllUser()
    }

    suspend fun updateData(notes: Notes) {
        notesDao.updateUser(notes)

    }

    suspend fun deleteSingleUser(notes: Notes) {
        notesDao.deleteUser(notes)
    }

}