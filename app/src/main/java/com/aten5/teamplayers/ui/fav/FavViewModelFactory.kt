package com.aten5.teamplayers.ui.fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aten5.teamplayers.repo.PlayersRepository
import javax.inject.Inject

class FavViewModelFactory @Inject constructor(private val playersRepository: PlayersRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavViewModel::class.java)) {
            return FavViewModel(
                playersRepository = playersRepository) as T
        }
        throw  IllegalArgumentException("Unknown Class")
    }

}
