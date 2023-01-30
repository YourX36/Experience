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

    private fun ticTacToe() = with(binding) {
        var activePlayer = 1
        h0V0.setOnClickListener {
            turn(h0V0, activePlayer)
            activePlayer = if (activePlayer == 1) 2 else 1
        }
        h0V1.setOnClickListener {
            turn(h0V1, activePlayer)
            activePlayer = if (activePlayer == 1) 2 else 1
        }
        h0V2.setOnClickListener {
            turn(h0V2, activePlayer)
            activePlayer = if (activePlayer == 1) 2 else 1
        }
        h1V0.setOnClickListener {
            turn(h1V0, activePlayer)
            activePlayer = if (activePlayer == 1) 2 else 1
        }
        h1V1.setOnClickListener {
            turn(h1V1, activePlayer)
            activePlayer = if (activePlayer == 1) 2 else 1
        }
        h1V2.setOnClickListener {
            turn(h1V2, activePlayer)
            activePlayer = if (activePlayer == 1) 2 else 1
        }
        h2V0.setOnClickListener {
            turn(h2V0, activePlayer)
            activePlayer = if (activePlayer == 1) 2 else 1
        }
        h2V1.setOnClickListener {
            turn(h2V1, activePlayer)
            activePlayer = if (activePlayer == 1) 2 else 1
        }
        h2V2.setOnClickListener {
            turn(h2V2, activePlayer)
            activePlayer = if (activePlayer == 1) 2 else 1
        }
    }

    private fun turn(view: androidx.appcompat.widget.AppCompatImageButton, activePlayer: Int)  {
        if (view.isClickable && activePlayer == 1) {
            view.isClickable = false
            view.setImageResource(R.drawable.ic_x)
        } else if (view.isClickable && activePlayer == 2)
        {
            view.isClickable = false
            view.setImageResource(R.drawable.ic_o)
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