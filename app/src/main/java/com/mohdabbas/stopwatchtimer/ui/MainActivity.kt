package com.mohdabbas.stopwatchtimer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mohdabbas.stopwatchtimer.R
import com.mohdabbas.stopwatchtimer.TabFragment
import com.mohdabbas.stopwatchtimer.Utils.getTabFragment
import com.mohdabbas.stopwatchtimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupTabLayout()
    }

    private val tabFragments =
        listOf(
            TabFragment(StopwatchFragment(), R.string.stopwatch_tab_text),
            TabFragment(TimerFragment(), R.string.timer_tab_text)
        )

    private fun setupViewPager() {
        viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(this, tabFragments)
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}