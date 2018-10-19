package apiteam.allpoisonim.features.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainPagerAdapter(private val fragments: List<Fragment>, fm: FragmentManager?)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}