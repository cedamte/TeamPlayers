package com.aten5.teamplayers.ui.fav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aten5.teamplayers.data.PlayerData
import com.aten5.teamplayers.repo.PlayersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel(private val playersRepository: PlayersRepository) : ViewModel() {
    val listOfPlayer: LiveData<List<PlayerData>> = playersRepository.getAllPlayers()

    fun removePlayer(playerData: PlayerData) {
        viewModelScope.launch(Dispatchers.IO) {
            playersRepository.removePlayer(playerData)
        }

    }
}
