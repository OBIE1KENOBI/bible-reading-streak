# Bible Reading Streak App

A native Android app for tracking your daily Bible reading and maintaining reading streaks.

## Features

✅ **Daily & Weekly Goal Setting** - Set your reading goals and track progress
✅ **Chapter/Verse Logging** - Log exactly which chapters and verses you read
✅ **Streak Tracking** - Current streak, longest streak, and weekly progress
✅ **Reading History** - View all your past readings in one place
✅ **Clean UI** - Simple, intuitive interface optimized for daily use

## Installation

### Prerequisites
- Android Studio
- Android SDK 24 or higher
- Kotlin support

### Steps

1. Clone this repository
   ```bash
   git clone https://github.com/OBIE1KENOBI/bible-reading-streak.git
   ```

2. Open in Android Studio
   - File → Open → Select the project folder

3. Connect your Huawei Nova Y61
   - Enable Developer Mode on your phone
   - Enable USB Debugging
   - Connect via USB cable

4. Build and Run
   - Click Run (or press Shift+F10)
   - Select your device
   - Wait for the app to install and launch

## How to Use

### Home Screen
- See your current streak, longest streak, and weekly progress
- Log today's reading by entering:
  - Book name (e.g., "Genesis")
  - Chapter number
  - Verse number (optional)
- Click "Log Reading" to save

### Log Screen
- View your complete reading history
- See all past readings with dates and chapters

### Settings Screen
- Set your daily reading goal (1-7 days)
- Set your weekly reading goal (1-7 days per week)
- Adjust with the seekbars

## Streak System

- **Current Streak**: Number of consecutive days you've read
- **Longest Streak**: Your best streak to date
- **Weekly Progress**: How many days this week you've read out of your goal

## Database

The app uses Room Database to store:
- Reading entries (date, book, chapter, verse)
- Automatically syncs with your device
- Data persists even after closing the app

## Technologies Used

- **Kotlin** - Modern Android development language
- **Room Database** - Local data persistence
- **LiveData** - Reactive data updates
- **ViewPager2** - Smooth navigation between screens
- **Material Design** - Modern UI components

## Support

For issues or suggestions, please create an issue in the GitHub repository.
