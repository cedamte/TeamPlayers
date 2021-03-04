package com.aten5.teamplayers.ui.fav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aten5.teamplayers.R
import com.aten5.teamplayers.app.TeamPlayers
import com.aten5.teamplayers.data.PlayerData
import com.aten5.teamplayers.databinding.ActivityFavBinding
import com.aten5.teamplayers.databinding.ListPlayersBinding
import com.aten5.teamplayers.di.AppComponent
import javax.inject.Inject

class FavActivity : AppCompatActivity(), OnRemoveClickLister {
    private lateinit var appComponent: AppComponent
    private lateinit var binding: ActivityFavBinding
    private lateinit var viewModel: FavViewModel

    @Inject
    lateinit var favViewModelFactory: FavViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as TeamPlayers).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_fav)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this, favViewModelFactory)
            .get(FavViewModel::class.java)

        val adapter = FavAdapter(this)

        binding.rvFav.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        binding.rvFav.adapter = adapter

        viewModel.listOfPlayer.observe(this, {
            adapter.setData(it)
        })
    }

    override fun onRemoveClick(playerData: PlayerData) {
        viewModel.removePlayer(playerData)
    }
}

class FavAdapter(private val onRemoveClickLister: OnRemoveClickLister) :
    RecyclerView.Adapter<FavAdapter.ViewModel>() {

    private val players = mutableListOf<PlayerData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel =
        ViewModel.from(parent = parent)

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        val data = players[position]
        holder.bind(data, onRemoveClickLister)
    }

    override fun getItemCount(): Int = players.size

    fun setData(data: List<PlayerData>) {
        players.clear()
        players.addAll(data)
        notifyDataSetChanged()
    }

    class ViewModel(private val binding: ListPlayersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlayerData, onRemoveClickLister: OnRemoveClickLister) {
            binding.player = item
            binding.btnRemove.setOnClickListener {
                onRemoveClickLister.onRemoveClick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewModel {
                val inflater = LayoutInflater.from(parent.context)
                val view: ListPlayersBinding =
                    DataBindingUtil.inflate(inflater, R.layout.list_players, parent, false)
                return ViewModel(view)
            }
        }
    }

}

interface OnRemoveClickLister {
    fun onRemoveClick(playerData: PlayerData)
}