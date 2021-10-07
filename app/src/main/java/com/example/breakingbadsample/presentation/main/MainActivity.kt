package com.example.breakingbadsample.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingbadsample.R
import com.example.breakingbadsample.databinding.ActivityMainBinding
import com.example.breakingbadsample.presentation.main.adapter.MainRecyclerViewAdapter
import com.example.breakingbadsample.presentation.main.adapter.MainSpacingItemDecoration
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
            rvCharacters.layoutManager = GridLayoutManager(this@MainActivity, 2)
            rvCharacters.addItemDecoration(MainSpacingItemDecoration(10))
            adapter = MainRecyclerViewAdapter(this@MainActivity)
            rvCharacters.adapter = adapter
        }
        subscribeLiveViewState()
    }

    private fun subscribeLiveViewState() {
        viewModel.liveViewState.observe(this) {
            if (it.charactersList != null) {
                adapter.setCharacters(it.charactersList)
            }
        }
    }


}