package com.example.biblereadingstreak.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.biblereadingstreak.ui.fragment.HomeFragment
import com.example.biblereadingstreak.ui.fragment.LogFragment
import com.example.biblereadingstreak.ui.fragment.SettingsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> LogFragment()
            2 -> SettingsFragment()
            else -> HomeFragment()
        }
    }
}
