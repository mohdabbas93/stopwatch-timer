package com.mohdabbas.stopwatchtimer.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Mohammad Abbas
 * On: 3/10/22.
 */
class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(StopwatchFragment(), TimerFragment())

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments.getOrNull(position)
            ?: throw IndexOutOfBoundsException("No fragment for position $position")
    }
}