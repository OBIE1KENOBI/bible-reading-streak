package com.example.biblereadingstreak.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_entries")
data class ReadingEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val book: String,
    val chapter: Int,
    val verse: Int = 0
)
