package com.aten5.teamplayers

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aten5.teamplayers.app.TeamPlayers
import com.aten5.teamplayers.data.*
import com.aten5.teamplayers.databinding.ActivityMainBinding
import com.aten5.teamplayers.di.AppComponent
import com.aten5.teamplayers.ui.AdapterViewHolder
import com.aten5.teamplayers.ui.HomeAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var appComponent: AppComponent
    private lateinit var binding: ActivityMainBinding
    private val playerAdapter = HomeAdapter<AdapterViewHolder>()
    private val teamAdapter = HomeAdapter<AdapterViewHolder>()
    private val listPlayerData = listOf(
        HeaderData("Players"),
        PlayerData(
            playerID = 1,
            name = "Messi",
            age = 99,
            club = "Super Fly FC",
            isFavorite = true
        ),
        PlayerData(
            playerID = 2,
            name = "Messi",
            age = 99,
            club = "Super Fly FC",
            isFavorite = true
        ),
        PlayerData(
            playerID = 3,
            name = "Messi",
            age = 99,
            club = "Super Fly FC",
            isFavorite = true
        ),
        PlayerData(
            playerID = 4,
            name = "Messi",
            age = 99,
            club = "Super Fly FC",
            isFavorite = true
        ),
        PlayerData(
            playerID = 5,
            name = "Messi",
            age = 99,
            club = "Super Fly FC",
            isFavorite = true
        ),
        PlayerData(
            playerID = 6,
            name = "Messi",
            age = 99,
            club = "Super Fly FC",
            isFavorite = true
        ),
        MoreButtonData("More Players"),
    )

    private val listTeamData = listOf(
        HeaderData("Teams"),
        TeamData(
            name = "Super Fly FC",
            city = "London",
            stadium = "Super Center"
        ),
        TeamData(
            name = "Super Fly FC",
            city = "London",
            stadium = "Super Center"
        ),
        TeamData(
            name = "Super Fly FC",
            city = "London",
            stadium = "Super Center"
        ),
        TeamData(
            name = "Super Fly FC",
            city = "London",
            stadium = "Super Center"
        ),
        TeamData(
            name = "Super Fly FC",
            city = "London",
            stadium = "Super Center"
        ),
        TeamData(
            name = "Super Fly FC",
            city = "London",
            stadium = "Super Center"
        ),
        MoreButtonData("More Teams")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as TeamPlayers).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rvPlayer.apply {
            adapter = playerAdapter
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            )
        }

        binding.rvTeam.apply {
            adapter = teamAdapter
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            )
        }

        playerAdapter.updateList(listPlayerData.map { it.getViewHolder() })

        teamAdapter.updateList(listTeamData.map { it.getViewHolder() })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }
}