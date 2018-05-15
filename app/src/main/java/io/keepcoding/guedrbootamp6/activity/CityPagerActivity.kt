package io.keepcoding.guedrbootamp6.activity

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import io.keepcoding.guedrbootamp6.R
import io.keepcoding.guedrbootamp6.fragment.ForecastFragment
import io.keepcoding.guedrbootamp6.model.Cities
import io.keepcoding.guedrbootamp6.model.City
import kotlinx.android.synthetic.main.activity_city_pager.*

class CityPagerActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CITY = "EXTRA_CITY"

        fun intent(context: Context, cityIndex: Int): Intent {
            val intent = Intent(context, CityPagerActivity::class.java)

            intent.putExtra(EXTRA_CITY, cityIndex)

            return intent
        }
    }

    private val cities = Cities()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_pager)

        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)

        val adapter = object: FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return ForecastFragment.newInstance(cities.getCity(position))
            }

            override  fun getCount() = cities.count

            override fun getPageTitle(position: Int): CharSequence {
                return cities.getCity(position).name
            }
        }

        view_pager.adapter = adapter

        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                updateCityInfo(position)
            }
        })

        val initialCityIndex = intent.getIntExtra(EXTRA_CITY, 0)
        moveToCity(initialCityIndex)
        updateCityInfo(initialCityIndex)
    }

    private fun updateCityInfo(position: Int) {
        //toolbar.title = cities.getCity(position).name
        supportActionBar?.title = cities.getCity(position).name
    }

    private fun moveToCity(position: Int) {
       view_pager.currentItem = position
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.pager_navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.previous -> {
            view_pager.currentItem = view_pager.currentItem - 1
            true
        }
        R.id.next -> {
            view_pager.currentItem = view_pager.currentItem + 1
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)

        val previousMenu: MenuItem? = menu?.findItem(R.id.previous)
        val nextMenu: MenuItem? = menu?.findItem(R.id.next)

        val adapter: PagerAdapter = view_pager.adapter!!
        previousMenu?.isEnabled = view_pager.currentItem > 0
        nextMenu?.isEnabled = view_pager.currentItem < adapter.count - 1
        return true
    }
}
