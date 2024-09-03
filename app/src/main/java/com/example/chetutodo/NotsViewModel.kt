package com.example.chetutodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotsViewModel(val repository: NotsRepository) : ViewModel() {

    fun saveData(notes: Notes) {
        viewModelScope.launch {
            repository.saveUser(notes)
        }
    }

    val notes = repository.notes

    fun deleteAllUser() {
        viewModelScope.launch {
            repository.deleteAllUser()
        }
    }

    fun updateData(notes: Notes) {
        viewModelScope.launch {
            repository.updateData(notes)
        }

    }

    fun deleteUser(notes: Notes) {
        viewModelScope.launch {
            repository.deleteSingleUser(notes)
        }
    }


}