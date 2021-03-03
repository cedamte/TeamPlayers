package com.aten5.teamplayers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aten5.teamplayers.repo.TeamsPlayerRepository
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val repository: TeamsPlayerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown Class")
    }
}