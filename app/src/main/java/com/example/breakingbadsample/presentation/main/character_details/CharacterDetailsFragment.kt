package com.example.breakingbadsample.presentation.main.character_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.breakingbadsample.app.consts.Keys
import com.example.breakingbadsample.databinding.FragmentCharacterDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterDetailsFragment : Fragment() {

    private val viewModel by viewModel<CharacterDetailsViewModel> {
        parametersOf(CharacterDetailsFragmentArgs.fromBundle(requireArguments()).arg)
    }
    private lateinit var binding: FragmentCharacterDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(Keys.KEY_TEST, true)
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {


        }
        viewModel.liveViewState.observe(viewLifecycleOwner) {
            binding.ivCharacter.setImageURI(it.characterModel?.img)
        }
    }



}