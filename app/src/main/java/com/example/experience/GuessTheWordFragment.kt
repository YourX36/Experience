package com.example.experience

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.experience.databinding.FragmentGuessTheWordBinding
import kotlin.random.Random


class GuessTheWordFragment : Fragment() {

    private var _binding: FragmentGuessTheWordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuessTheWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        val words = createListWords()
        val randomWord = createRandomWord(words)
        textView.isSelected = true
        textView.text = words.joinToString(" ")
        etEnter.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER) {
                    onGame(randomWord, userWord())
                    return true
                }
                return false
            }

        })
    }

    private fun onGame(guessedWordArray: CharArray, playerWordArray: CharArray) = with(binding) {
        val resultArray = CharArray(15)
        for (i in resultArray.indices) {
            resultArray[i] = '#'
        }
        txtResult.text = resultArray.joinToString("")
        txtHelp.text = guessedWordArray.joinToString("")

        if (playerWordArray.contentEquals(guessedWordArray)) {
            openFragment(SuccessGameFragment.newInstance())

        } else {
            for (i in guessedWordArray.indices) {
                if (i >= playerWordArray.size) {
                    break
                }
                if (playerWordArray[i] == guessedWordArray[i]) {
                    resultArray[i] = guessedWordArray[i]
                    txtResult.text = resultArray.joinToString("")
                }
            }
        }
    }

    private fun createListWords(): List<String> {
        return listOf(
            "apple",
            "orange",
            "lemon",
            "banana",
            "apricot",
            "avocado",
            "broccoli",
            "carrot",
            "cherry",
            "garlic",
            "grape",
            "melon",
            "leak",
            "kiwi",
            "mango",
            "mushroom",
            "nut",
            "olive",
            "pea",
            "peanut",
            "pear",
            "pepper",
            "pineapple",
            "pumpkin",
            "potato"
        )
    }

    private fun createRandomWord(words: List<String>): CharArray {
        val guessedWord = Random.nextInt(words.size)
        return words[guessedWord].toCharArray()
    }

    private fun userWord() : CharArray = with(binding) {
        val playerWord = etEnter.text.toString()
        return@with playerWord.toCharArray()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = GuessTheWordFragment()
    }
}