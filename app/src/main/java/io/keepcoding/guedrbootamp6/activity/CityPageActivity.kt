package io.keepcoding.guedrbootamp6.activity

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.Menu
import android.view.MenuItem
import io.keepcoding.guedrbootamp6.R
import io.keepcoding.guedrbootamp6.fragment.ForecastFragment
import io.keepcoding.guedrbootamp6.model.Cities
import kotlinx.android.synthetic.main.activity_city_page.*

class CityPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_page)

        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)

        val cities = Cities()

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
