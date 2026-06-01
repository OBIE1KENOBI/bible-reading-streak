package com.example.biblereadingstreak.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.biblereadingstreak.BibleReadingViewModel
import com.example.biblereadingstreak.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BibleReadingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(BibleReadingViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentStreak.observe(viewLifecycleOwner) { streak ->
            binding.currentStreakText.text = "$streak"
        }

        viewModel.longestStreak.observe(viewLifecycleOwner) { longest ->
            binding.longestStreakText.text = "$longest"
        }

        viewModel.weeklyProgress.observe(viewLifecycleOwner) { progress ->
            binding.weeklyProgressText.text = "$progress"
        }

        viewModel.weeklyGoal.observe(viewLifecycleOwner) { goal ->
            binding.weeklyGoalText.text = "/ $goal"
        }

        binding.addReadingBtn.setOnClickListener {
            val book = binding.bookInput.text.toString()
            val chapter = binding.chapterInput.text.toString().toIntOrNull() ?: 1
            val verse = binding.verseInput.text.toString().toIntOrNull() ?: 0

            if (book.isNotEmpty()) {
                viewModel.addReading(book, chapter, verse)
                binding.bookInput.text.clear()
                binding.chapterInput.text.clear()
                binding.verseInput.text.clear()
                binding.feedbackText.text = "✓ Reading logged!"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
