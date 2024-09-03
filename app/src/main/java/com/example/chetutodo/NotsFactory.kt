package com.example.chetutodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotsFactory(private val repository: NotsRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotsViewModel::class.java)){
            return NotsViewModel(repository) as T

        }
        throw IllegalArgumentException("unknown Exception")
    }
}