package com.example.biblereadingstreak.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.biblereadingstreak.BibleReadingViewModel
import com.example.biblereadingstreak.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BibleReadingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(BibleReadingViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Daily Goal
        binding.dailyGoalSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val goal = if (progress == 0) 1 else progress
                binding.dailyGoalValue.text = "$goal day(s)"
                viewModel.setDailyGoal(goal)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Weekly Goal
        binding.weeklyGoalSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val goal = if (progress == 0) 1 else progress
                binding.weeklyGoalValue.text = "$goal day(s) per week"
                viewModel.setWeeklyGoal(goal)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        viewModel.dailyGoal.observe(viewLifecycleOwner) { goal ->
            binding.dailyGoalSeekBar.progress = goal
        }

        viewModel.weeklyGoal.observe(viewLifecycleOwner) { goal ->
            binding.weeklyGoalSeekBar.progress = goal
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
