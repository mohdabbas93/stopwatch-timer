package com.mohdabbas.stopwatchtimer.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mohdabbas.stopwatchtimer.TabFragment
import com.mohdabbas.stopwatchtimer.Utils.getTabFragment

/**
 * Created by Mohammad Abbas
 * On: 3/10/22.
 */
class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val tabFragments: List<TabFragment>
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = tabFragments.size

    override fun createFragment(position: Int): Fragment {
        return tabFragments.getTabFragment(position).fragment
    }
}