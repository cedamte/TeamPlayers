package com.aten5.teamplayers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aten5.teamplayers.BuildConfig
import com.aten5.teamplayers.data.PlayerData
import com.aten5.teamplayers.data.TeamData
import com.aten5.teamplayers.repo.TeamsPlayerRepository


class HomeViewModel(private val repository: TeamsPlayerRepository) : ViewModel() {
    private val _playersObservable: MutableLiveData<List<PlayerData>> = MutableLiveData()
    val playersObservable: LiveData<List<PlayerData>>
        get() = _playersObservable

    private val _teamsObservable: MutableLiveData<List<TeamData>> = MutableLiveData()
    val teamsObservable: LiveData<List<TeamData>>
        get() = _teamsObservable


    private val _loadingObservable: MutableLiveData<Boolean> = MutableLiveData()
    val loadingObservable: LiveData<Boolean>
        get() = _loadingObservable
    private val _errorObservable: MutableLiveData<String> = MutableLiveData()
    val errorObservable: LiveData<String>
        get() = _errorObservable


    fun getTeamsPlayers(searchString: String) {
        repository.getTeamsPlayers(searchString)
            .subscribe({ data ->
                _playersObservable.value = data.result.players.map {
                    PlayerData(
                        playerID = it.playerID.toInt(),
                        name = "${it.playerFirstName} ${it.playerSecondName}",
                        age = it.playerAge.toInt(),
                        club = it.playerClub,
                    )
                }
                _teamsObservable.value = data.result.teams.map {
                    TeamData(
                        teamID = it.teamID.toInt(),
                        name = it.teamName,
                        city = it.teamCity,
                        stadium = it.teamStadium
                    )
                }

            }, {
                _errorObservable.value = it.message ?: "Unknown error"

            })

    }

    fun getMorePlayers(searchString: String) {
        repository.getMore(
            searchString = searchString,
            searchType = BuildConfig.SEARCH_TYPE_PLAYER,
            offset = BuildConfig.OFFSET
        ).subscribe({ data ->
            _playersObservable.value = data.result.players.map {
                PlayerData(
                    playerID = it.playerID.toInt(),
                    name = "${it.playerFirstName} ${it.playerSecondName}",
                    age = it.playerAge.toInt(),
                    club = it.playerClub,
                )
            }
        }, { _errorObservable.value = it.message ?: "Unknown error" })
    }

    fun getMoreTeams(searchString: String) {
        repository.getMore(
            searchString = searchString,
            searchType = BuildConfig.SEARCH_TYPE_TEAMS,
            offset = BuildConfig.OFFSET
        ).subscribe({ data ->
            _playersObservable.value = data.result.players.map {
                PlayerData(
                    playerID = it.playerID.toInt(),
                    name = "${it.playerFirstName} ${it.playerSecondName}",
                    age = it.playerAge.toInt(),
                    club = it.playerClub,
                )
            }
        }, { _errorObservable.value = it.message ?: "Unknown error" })
    }
}