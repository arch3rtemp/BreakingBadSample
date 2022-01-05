package com.example.breakingbadsample.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.breakingbadsample.NavGraphDirections
import com.example.breakingbadsample.R
import com.example.breakingbadsample.app.service.MessagingService
import com.example.breakingbadsample.databinding.ActivityMainBinding
import com.example.breakingbadsample.domain.models.data_models.CharacterDetailsArgModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseNotification(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        parseNotification(intent)
    }

    fun parseNotification(intent: Intent) {
        val charId = intent.getStringExtra(MessagingService.KEY_CHAR_ID)?.toInt()
        if (charId != null) {
            findNavController(R.id.navHost).navigate(NavGraphDirections.actionGlobalCharacterDetailsFragment(
                CharacterDetailsArgModel(charId)
            ))
        }
    }
}