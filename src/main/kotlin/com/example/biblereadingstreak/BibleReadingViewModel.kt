package com.example.biblereadingstreak

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.biblereadingstreak.database.ReadingEntry
import com.example.biblereadingstreak.database.AppDatabase
import kotlinx.coroutines.launch
import java.time.LocalDate

class BibleReadingViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val readingDao = db.readingDao()

    val allReadings: LiveData<List<ReadingEntry>> = readingDao.getAllReadings()

    private val _currentStreak = MutableLiveData<Int>()
    val currentStreak: LiveData<Int> = _currentStreak

    private val _longestStreak = MutableLiveData<Int>()
    val longestStreak: LiveData<Int> = _longestStreak

    private val _weeklyProgress = MutableLiveData<Int>()
    val weeklyProgress: LiveData<Int> = _weeklyProgress

    private val _dailyGoal = MutableLiveData<Int>()
    val dailyGoal: LiveData<Int> = _dailyGoal

    private val _weeklyGoal = MutableLiveData<Int>()
    val weeklyGoal: LiveData<Int> = _weeklyGoal

    init {
        loadGoals()
        calculateStreaks()
    }

    fun addReading(book: String, chapter: Int, verse: Int = 0) {
        viewModelScope.launch {
            val entry = ReadingEntry(
                date = LocalDate.now().toString(),
                book = book,
                chapter = chapter,
                verse = verse
            )
            readingDao.insertReading(entry)
            calculateStreaks()
        }
    }

    fun calculateStreaks() {
        viewModelScope.launch {
            val readings = readingDao.getAllReadingsSync()
            if (readings.isEmpty()) {
                _currentStreak.postValue(0)
                _longestStreak.postValue(0)
                return@launch
            }

            val sortedReadings = readings.sortedByDescending { it.date }
            var currentStreak = 0
            var longestStreak = 0
            var tempStreak = 1
            var lastDate = LocalDate.parse(sortedReadings[0].date)

            for (i in 1 until sortedReadings.size) {
                val currentDate = LocalDate.parse(sortedReadings[i].date)
                val daysDiff = lastDate.toEpochDay() - currentDate.toEpochDay()

                if (daysDiff == 1L) {
                    tempStreak++
                } else if (daysDiff > 1) {
                    if (tempStreak > longestStreak) longestStreak = tempStreak
                    tempStreak = 1
                }
                lastDate = currentDate
            }

            if (tempStreak > longestStreak) longestStreak = tempStreak

            // Calculate current streak (if today or yesterday)
            val today = LocalDate.now()
            val yesterday = today.minusDays(1)
            val mostRecentDate = LocalDate.parse(sortedReadings[0].date)

            if (mostRecentDate == today || mostRecentDate == yesterday) {
                currentStreak = 1
                var checkDate = mostRecentDate.minusDays(1)
                for (reading in sortedReadings.drop(1)) {
                    val readingDate = LocalDate.parse(reading.date)
                    if (readingDate == checkDate) {
                        currentStreak++
                        checkDate = checkDate.minusDays(1)
                    } else if (readingDate.isBefore(checkDate)) {
                        break
                    }
                }
            }

            _currentStreak.postValue(currentStreak)
            _longestStreak.postValue(longestStreak)
            updateWeeklyProgress()
        }
    }

    private fun updateWeeklyProgress() {
        viewModelScope.launch {
            val today = LocalDate.now()
            val weekStart = today.minusDays(today.dayOfWeek.value.toLong() - 1)
            val readings = readingDao.getAllReadingsSync()
            val weekReadings = readings.filter {
                val readingDate = LocalDate.parse(it.date)
                readingDate >= weekStart && readingDate <= today
            }
            _weeklyProgress.postValue(weekReadings.size)
        }
    }

    fun setDailyGoal(goal: Int) {
        viewModelScope.launch {
            _dailyGoal.postValue(goal)
        }
    }

    fun setWeeklyGoal(goal: Int) {
        viewModelScope.launch {
            _weeklyGoal.postValue(goal)
        }
    }

    private fun loadGoals() {
        _dailyGoal.postValue(1)
        _weeklyGoal.postValue(5)
    }

    fun getReadingsForDate(date: LocalDate): LiveData<List<ReadingEntry>> {
        return readingDao.getReadingsForDate(date.toString())
    }
}
