package com.example.breakingbadsample.presentation.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.breakingbadsample.app.consts.Defaults
import com.example.breakingbadsample.app.consts.Keys
import com.example.breakingbadsample.databinding.ActivityCharacterDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModel<CharacterDetailsViewModel> {
        parametersOf(intent.getIntExtra(Keys.KEY_CHARACTER_ID, Defaults.INVALID_ID))
    }

    private lateinit var binding: ActivityCharacterDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeLiveViewState()
    }

    private fun subscribeLiveViewState() {
        with(binding) {
            viewModel.liveViewState.observe(this@CharacterDetailsActivity) { state ->
                if (state.characterModel != null) {
                    ivCharacter.setImageURI(state.characterModel.img)
                }
            }
        }
    }
}