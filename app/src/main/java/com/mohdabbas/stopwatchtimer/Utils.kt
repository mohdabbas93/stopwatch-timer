package com.mohdabbas.stopwatchtimer

/**
 * Created by Mohammad Abbas
 * On: 3/10/22.
 */
object Utils {
    fun List<TabFragment>.getTabFragment(position: Int): TabFragment {
        return getOrNull(position)
            ?: throw IndexOutOfBoundsException("No fragment for position $position")
    }
}