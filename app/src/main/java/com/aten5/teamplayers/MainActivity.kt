package com.aten5.teamplayers

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aten5.teamplayers.app.TeamPlayers
import com.aten5.teamplayers.data.*
import com.aten5.teamplayers.databinding.ActivityMainBinding
import com.aten5.teamplayers.di.AppComponent
import com.aten5.teamplayers.ui.AdapterViewHolder
import com.aten5.teamplayers.ui.HomeAdapter
import com.aten5.teamplayers.ui.HomeViewModel
import com.aten5.teamplayers.ui.HomeViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var appComponent: AppComponent
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    private val playerAdapter = HomeAdapter<AdapterViewHolder>()
    private val teamAdapter = HomeAdapter<AdapterViewHolder>()

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as TeamPlayers).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this, homeViewModelFactory)
            .get(HomeViewModel::class.java)


        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query: String ->
                Timber.d("✅ $query")
                viewModel.getTeamsPlayers(searchString = query)
            }
        }


        viewModel.playersObservable.observe(this, {
            val newList = mutableListOf<AdapterViewHolder>()
            newList.add(HeaderData("Player").getViewHolder())
            newList.addAll(it.map { it.getViewHolder() }.toList())
            newList.add(MoreButtonData("More Players").getViewHolder())
            playerAdapter.updateList(newList)
        })

        viewModel.teamsObservable.observe(this, {
            val newList = mutableListOf<AdapterViewHolder>()
            newList.add(HeaderData("Teams").getViewHolder())
            newList.addAll(it.map { it.getViewHolder() })
            newList.add(MoreButtonData("More Teams").getViewHolder())
            teamAdapter.updateList(newList)

        })

        binding.rvPlayer.apply {
            adapter = playerAdapter
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            )
            setHasFixedSize(true)
        }

        binding.rvTeam.apply {
            adapter = teamAdapter
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            )
            setHasFixedSize(true)
        }

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