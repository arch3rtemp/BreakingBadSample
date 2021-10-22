package com.example.breakingbadsample.presentation.main.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.breakingbadsample.R
import com.example.breakingbadsample.app.consts.Keys
import com.example.breakingbadsample.databinding.FragmentCharactersBinding
import com.example.breakingbadsample.domain.models.CharacterModel
import com.example.breakingbadsample.domain.models.data_models.CharacterDetailsArgModel
import com.example.breakingbadsample.presentation.main.characters.drawers.CharacterItemDrawer
import com.example.breakingbadsample.presentation.main.characters.drawers.TitleItemDrawer
import com.tbcbank.business.universalAdapter.adapters.UniversalAdapter
import com.tbcbank.business.universalAdapter.builders.UniversalAdapterBuilder
import com.tbcbank.business.universalAdapter.models.ItemDrawer
import com.tbcbank.business.universalAdapter.viewholders.UniversalViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private lateinit var adapter: UniversalAdapter<ItemDrawer>
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel by viewModel<CharactersViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvCharacters.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = UniversalAdapterBuilder<ItemDrawer>()
                .setViewHolderCreationListener {
                    if(it.itemView.id == R.id.clRoot){
                        (it.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
                            .isFullSpan = true
                    }
                }
                .setAreItemsTheSameCallback { item1, item2 ->
                    if (item1 is CharacterItemDrawer && item2 is CharacterItemDrawer) {
                        item1.model.charId == item2.model.charId
                    } else if (item1 is TitleItemDrawer && item2 is TitleItemDrawer) {
                        item1.title == item2.title
                    } else {
                        false
                    }
                }
                .setAreContentsTheSameCallback { _, _ ->
                    true
                }
                .buildAdapterWith(rvCharacters)
            adapter.registerClickListener(CharacterItemDrawer::class){ characterItemDrawer: CharacterItemDrawer, _: UniversalViewHolder ->
                goToDetailsScreen(characterItemDrawer.model)
            }
        }
        subscribeLiveViewState()
    }

    override fun onResume() {
        super.onResume()
        val test = findNavController().currentBackStackEntry?.savedStateHandle?.remove<Boolean>(Keys.KEY_TEST)
        if (test == true) {
            Toast.makeText(requireContext(), "Congratulations!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToDetailsScreen(characterModel: CharacterModel) {
        findNavController().navigate(CharactersFragmentDirections.actionCharacterFragmentToCharacterDetailsFragment(
            CharacterDetailsArgModel(characterModel.charId)
        ))
    }

    private fun subscribeLiveViewState() {
        viewModel.liveViewState.observe(viewLifecycleOwner) { state ->
            if (state.charactersList != null) {
                val items = state.charactersList
                    .sortedBy { it.name }
                    .groupBy { it.name.first() }
                    .flatMap { entry ->
                        mutableListOf<ItemDrawer>()
                            .apply {
                                add(TitleItemDrawer(entry.key.toString()))
                                addAll(entry.value.map { CharacterItemDrawer(it) })
                            }
                    }

                adapter.updateAll(items,false)
            }
        }
    }
}