package com.example.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.experience.databinding.FragmentSuccessGameBinding

class SuccessGameFragment : Fragment() {

    private var _binding: FragmentSuccessGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuccessGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        bundle?.let {
            if (bundle.getChar(WINNER) == FIRST_PLAYER_TAG) {
                tvWinner.isVisible = true
                tvWinner.text = "Победил первый игрок!"
            }
            if (bundle.getChar(WINNER) == SECOND_PLAYER_TAG) {
                tvWinner.isVisible = true
                tvWinner.text = "Победил второй игрок!"
            }
        }
        btnMainMenu.setOnClickListener { openFragment(MainScreenFragment.newInstance()) }
        btnRetry.setOnClickListener {
            if (bundle?.getString(SCREEN) == TIC_TAC_TOE) {
                openFragment(TicTacToeFragment.newInstance())
            }
            if (bundle?.getString(SCREEN) == GUESS_THE_WORD) openFragment(GuessTheWordFragment.newInstance())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun openFragment(fragment: Fragment) {
        val manager = activity?.supportFragmentManager
        manager?.let {
            manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        fun newInstance() = SuccessGameFragment()
        private const val FIRST_PLAYER_TAG = 'X'
        private const val SECOND_PLAYER_TAG = 'O'
        private const val WINNER = "WINNER"
        private const val SCREEN = "SCREEN"
        private const val GUESS_THE_WORD = "GUESS_THE_WORD"
        private const val TIC_TAC_TOE = "TIC_TAC_TOE"
    }
}