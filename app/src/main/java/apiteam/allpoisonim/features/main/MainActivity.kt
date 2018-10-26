package apiteam.allpoisonim.features.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import apiteam.allpoisonim.R
import apiteam.allpoisonim.features.books.BooksFragment
import apiteam.allpoisonim.features.mypage.MyPageFragment
import apiteam.allpoisonim.api.data.UserModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        val fragments = listOf(HomeFragment(), BooksFragment(), MyPageFragment())

        vp_main.adapter = MainPagerAdapter(fragments, supportFragmentManager)
        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    vp_main.currentItem = 1
                    Toast.makeText(this@MainActivity,"준비중인 서비스입니다 ㅠ_ㅠ 흙흙",Toast.LENGTH_SHORT).show()
                } else {
                    bottom_navigation.menu.getItem(position).isChecked = true
                }
            }
        })
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_home -> vp_main.currentItem = 0
                R.id.item_books -> vp_main.currentItem = 1
                R.id.item_my_page -> {
                    Toast.makeText(this,"준비중인 서비스입니다 ㅠ_ㅠ 흙흙",Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener false
                }
            }
            true
        }
    }

}
