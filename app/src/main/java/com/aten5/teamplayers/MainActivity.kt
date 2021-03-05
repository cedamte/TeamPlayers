package com.aten5.teamplayers

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
import com.aten5.teamplayers.ui.*
import com.aten5.teamplayers.ui.fav.FavActivity
import com.aten5.teamplayers.ui.viewholders.MoreButtonViewHolder
import com.aten5.teamplayers.ui.viewholders.PlayerViewHolder
import com.aten5.teamplayers.ui.viewholders.TeamViewHolder
import kotlinx.android.synthetic.main.player_holder.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnMoreClickLister, OnAddClickLister {
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
            newList.add(HeaderData(id = 1, title = "Player").getViewHolder())
            newList.addAll(it.map {
                val playerViewHolder = it.getViewHolder() as PlayerViewHolder
                playerViewHolder.setOnClickListener(this@MainActivity)
                playerViewHolder
            })
            newList.add(MoreButtonData(id = 1, title = "More Players").getViewHolder().apply {
                this as MoreButtonViewHolder
                this.setOnClickListener(this@MainActivity)
            }
            )
            playerAdapter.updateList(newList)
        })

        viewModel.teamsObservable.observe(this, {
            val newList = mutableListOf<AdapterViewHolder>()
            newList.add(HeaderData(id = 1, title = "Teams").getViewHolder())
            newList.addAll(it.map { it.getViewHolder() })
            newList.add(
                MoreButtonData(id = 1, title = "More Teams").getViewHolder().apply {
                    this as MoreButtonViewHolder
                    this.setOnClickListener(this@MainActivity)
                }
            )
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fav_players -> {
                launchFavActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onMoreClick(searchType: String) {
        when (searchType) {
            "More Players" -> {
                intent.getStringExtra(SearchManager.QUERY)?.also { query: String ->
                    viewModel.getMorePlayers(searchString = query)
                }
            }
            "More Teams" -> {
                intent.getStringExtra(SearchManager.QUERY)?.also { query: String ->
                    viewModel.getMoreTeams(searchString = query)
                }

            }
        }
    }

    override fun onAddClick(playerData: PlayerData) {
        val addPlayerBottomSheet = AddPlayerBottomSheet.newInstance {
            viewModel.onAddOptionSelected(it, playerData)
        }
        addPlayerBottomSheet.show(supportFragmentManager, ADD_PLAYER_BOTTOM_SHEET_TAG)

    }

    private fun launchFavActivity() {
        val intent = Intent(this, FavActivity::class.java)
        startActivity(intent)
    }


    companion object {
        const val ADD_PLAYER_BOTTOM_SHEET_TAG = "add_player"
    }

}