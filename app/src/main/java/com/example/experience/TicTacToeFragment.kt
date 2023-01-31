package com.example.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private fun turn(view: androidx.appcompat.widget.AppCompatImageButton, activePlayer: Players) : Players  {
        if (view.isClickable && activePlayer == Players.FirstPlayer) {
            view.isClickable = false
            view.setImageResource(R.drawable.ic_x)
            return Players.SecondPlayer

        } else if (view.isClickable && activePlayer == Players.SecondPlayer)
        {
            view.isClickable = false
            view.setImageResource(R.drawable.ic_o)
            return Players.FirstPlayer
        } else {
            return Players.FirstPlayer
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
        fun newInstance() = TicTacToeFragment()
    }
}