package com.example.biblereadingstreak.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReadingDao {
    @Insert
    suspend fun insertReading(entry: ReadingEntry)

    @Query("SELECT * FROM reading_entries ORDER BY date DESC")
    fun getAllReadings(): LiveData<List<ReadingEntry>>

    @Query("SELECT * FROM reading_entries ORDER BY date DESC")
    suspend fun getAllReadingsSync(): List<ReadingEntry>

    @Query("SELECT * FROM reading_entries WHERE date = :date ORDER BY date DESC")
    fun getReadingsForDate(date: String): LiveData<List<ReadingEntry>>

    @Query("DELETE FROM reading_entries WHERE id = :id")
    suspend fun deleteReading(id: Int)
}
