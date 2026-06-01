package com.example.biblereadingstreak.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biblereadingstreak.BibleReadingViewModel
import com.example.biblereadingstreak.databinding.FragmentLogBinding
import com.example.biblereadingstreak.ui.adapter.ReadingAdapter

class LogFragment : Fragment() {
    private var _binding: FragmentLogBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BibleReadingViewModel
    private lateinit var adapter: ReadingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(BibleReadingViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ReadingAdapter()
        binding.readingRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.readingRecyclerView.adapter = adapter

        viewModel.allReadings.observe(viewLifecycleOwner) { readings ->
            adapter.submitList(readings)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
