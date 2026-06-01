package com.example.biblereadingstreak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.biblereadingstreak.database.ReadingEntry
import com.example.biblereadingstreak.databinding.ItemReadingBinding

class ReadingAdapter : ListAdapter<ReadingEntry, ReadingAdapter.ReadingViewHolder>(ReadingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingViewHolder {
        val binding = ItemReadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReadingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReadingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ReadingViewHolder(private val binding: ItemReadingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: ReadingEntry) {
            binding.bookName.text = entry.book
            binding.chapterVerse.text = "Chapter ${entry.chapter}${if (entry.verse > 0) ":${entry.verse}" else ""}"
            binding.readingDate.text = entry.date
        }
    }

    class ReadingDiffCallback : DiffUtil.ItemCallback<ReadingEntry>() {
        override fun areItemsTheSame(oldItem: ReadingEntry, newItem: ReadingEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReadingEntry, newItem: ReadingEntry): Boolean {
            return oldItem == newItem
        }
    }
}
