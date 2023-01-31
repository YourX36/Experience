package com.example.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.experience.databinding.FragmentTicTacToeBinding


class TicTacToeFragment : Fragment() {

    private var _binding: FragmentTicTacToeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicTacToeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        ticTacToe()
    }

    private enum class Players() {
        FirstPlayer,
        SecondPlayer
    }

    private fun ticTacToe() = with(binding) {
        var activePlayer = Players.FirstPlayer
        h0V0.setOnClickListener {
            activePlayer = turn(h0V0, activePlayer)
        }
        h0V1.setOnClickListener {
            activePlayer = turn(h0V1, activePlayer)
        }
        h0V2.setOnClickListener {
            activePlayer = turn(h0V2, activePlayer)
        }
        h1V0.setOnClickListener {
            activePlayer = turn(h1V0, activePlayer)
        }
        h1V1.setOnClickListener {
            activePlayer = turn(h1V1, activePlayer)
        }
        h1V2.setOnClickListener {
            activePlayer = turn(h1V2, activePlayer)
        }
        h2V0.setOnClickListener {
            activePlayer = turn(h2V0, activePlayer)
        }
        h2V1.setOnClickListener {
            activePlayer = turn(h2V1, activePlayer)
        }
        h2V2.setOnClickListener {
            activePlayer = turn(h2V2, activePlayer)
        }
    }

    private fun turn(
        view: androidx.appcompat.widget.AppCompatImageButton,
        activePlayer: Players
    ): Players {
        if (view.isClickable && activePlayer == Players.FirstPlayer) {
            view.isClickable = false
            view.setImageResource(R.drawable.ic_x)
            view.tag = FIRST_PLAYER_TAG
            checkToWin()
            return Players.SecondPlayer

        } else if (view.isClickable && activePlayer == Players.SecondPlayer) {
            view.isClickable = false
            view.setImageResource(R.drawable.ic_o)
            view.tag = SECOND_PLAYER_TAG
            checkToWin()
            return Players.FirstPlayer
        } else {
            return Players.FirstPlayer
        }
    }

    private fun checkToWin() = with(binding) {
        if (h0V0.tag == FIRST_PLAYER_TAG && h0V1.tag == FIRST_PLAYER_TAG && h0V2.tag == FIRST_PLAYER_TAG) {
            playerWin(FIRST_PLAYER_TAG)
        }
        if (h0V0.tag == SECOND_PLAYER_TAG && h0V1.tag == SECOND_PLAYER_TAG && h0V2.tag == SECOND_PLAYER_TAG) {
            playerWin(SECOND_PLAYER_TAG)
        }
        if (h1V0.tag == FIRST_PLAYER_TAG && h1V1.tag == FIRST_PLAYER_TAG && h1V2.tag == FIRST_PLAYER_TAG) {
            playerWin(FIRST_PLAYER_TAG)
        }
        if (h1V0.tag == SECOND_PLAYER_TAG && h1V1.tag == SECOND_PLAYER_TAG && h1V2.tag == SECOND_PLAYER_TAG) {
            playerWin(SECOND_PLAYER_TAG)
        }
        if (h2V0.tag == FIRST_PLAYER_TAG && h2V1.tag == FIRST_PLAYER_TAG && h2V2.tag == FIRST_PLAYER_TAG) {
            playerWin(FIRST_PLAYER_TAG)
        }
        if (h2V0.tag == SECOND_PLAYER_TAG && h2V1.tag == SECOND_PLAYER_TAG && h2V2.tag == SECOND_PLAYER_TAG) {
            playerWin(SECOND_PLAYER_TAG)
        }
        if (h0V0.tag == FIRST_PLAYER_TAG && h1V0.tag == FIRST_PLAYER_TAG && h2V0.tag == FIRST_PLAYER_TAG) {
            playerWin(FIRST_PLAYER_TAG)
        }
        if (h0V0.tag == SECOND_PLAYER_TAG && h1V0.tag == SECOND_PLAYER_TAG && h2V0.tag == SECOND_PLAYER_TAG) {
            playerWin(SECOND_PLAYER_TAG)
        }
        if (h0V1.tag == FIRST_PLAYER_TAG && h1V1.tag == FIRST_PLAYER_TAG && h2V1.tag == FIRST_PLAYER_TAG) {
            playerWin(FIRST_PLAYER_TAG)
        }
        if (h0V1.tag == SECOND_PLAYER_TAG && h1V1.tag == SECOND_PLAYER_TAG && h2V1.tag == SECOND_PLAYER_TAG) {
            playerWin(SECOND_PLAYER_TAG)
        }
        if (h0V2.tag == FIRST_PLAYER_TAG && h1V2.tag == FIRST_PLAYER_TAG && h2V2.tag == FIRST_PLAYER_TAG) {
            playerWin(FIRST_PLAYER_TAG)
        }
        if (h0V2.tag == SECOND_PLAYER_TAG && h1V2.tag == SECOND_PLAYER_TAG && h2V2.tag == SECOND_PLAYER_TAG) {
            playerWin(SECOND_PLAYER_TAG)
        }
        if (h0V0.tag == FIRST_PLAYER_TAG && h1V1.tag == FIRST_PLAYER_TAG && h2V2.tag == FIRST_PLAYER_TAG) {
            playerWin(FIRST_PLAYER_TAG)
        }
        if (h0V0.tag == SECOND_PLAYER_TAG && h1V1.tag == SECOND_PLAYER_TAG && h2V2.tag == SECOND_PLAYER_TAG) {
            playerWin(SECOND_PLAYER_TAG)
        }
        if (h2V0.tag == FIRST_PLAYER_TAG && h1V1.tag == FIRST_PLAYER_TAG && h0V2.tag == FIRST_PLAYER_TAG) {
            playerWin(FIRST_PLAYER_TAG)
        }
        if (h2V0.tag == SECOND_PLAYER_TAG && h1V1.tag == SECOND_PLAYER_TAG && h0V2.tag == SECOND_PLAYER_TAG) {
            playerWin(SECOND_PLAYER_TAG)
        }
    }

    private fun playerWin(tag: Char) {
        val bundle = bundleOf(
            WINNER to tag,
            SCREEN to TIC_TAC_TOE
        )
        openFragment(SuccessGameFragment.newInstance(), bundle)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun openFragment(fragment: Fragment, bundle: Bundle) {
        val manager = activity?.supportFragmentManager
        fragment.arguments = bundle
        manager?.let {
            manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        fun newInstance() = TicTacToeFragment()
        private const val FIRST_PLAYER_TAG = 'X'
        private const val SECOND_PLAYER_TAG = 'O'
        private const val WINNER = "WINNER"
        private const val SCREEN = "SCREEN"
        private const val TIC_TAC_TOE = "TIC_TAC_TOE"
    }
}