package com.example.breakingbadsample.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.breakingbadsample.app.consts.Keys
import com.example.breakingbadsample.databinding.ActivityMainBinding
import com.example.breakingbadsample.domain.models.CharacterModel
import com.example.breakingbadsample.presentation.character.CharacterDetailsActivity
import com.example.breakingbadsample.presentation.main.adapter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            rvCharacters.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvCharacters.addItemDecoration(MainSpacingItemDecoration(10))
            adapter = MainRecyclerViewAdapter{
                goToDetailsScreen(it)
            }
            rvCharacters.adapter = adapter
        }
        subscribeLiveViewState()
    }

    private fun goToDetailsScreen(characterModel: CharacterModel) {
        startActivity(Intent(this, CharacterDetailsActivity::class.java).apply {
            putExtra(Keys.KEY_CHARACTER_ID, characterModel.charId)
        })
    }

    private fun subscribeLiveViewState() {
        viewModel.liveViewState.observe(this) { state ->
            if (state.charactersList != null) {
                val items = state.charactersList
                    .sortedBy { it.name }
                    .groupBy { it.name.first() }
                    .flatMap { entry ->
                        mutableListOf<CharacterListItem>()
                            .apply {
                                add(TitleItem(entry.key.toString()))
                                addAll(entry.value.map { CharacterItem(it) })
                            }
                    }
                adapter.setCharacters(items)
            }
        }
    }


}