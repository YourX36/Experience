package com.example.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.experience.databinding.FragmentSuccessGameBinding

class SuccessGameFragment: Fragment() {

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
        btnMainMenu.setOnClickListener { openFragment(MainScreenFragment.newInstance()) }
        btnRetry.setOnClickListener { openFragment(GuessTheWordFragment.newInstance()) }
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
    }
}