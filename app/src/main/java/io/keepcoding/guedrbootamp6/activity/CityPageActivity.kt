package io.keepcoding.guedrbootamp6.activity

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import io.keepcoding.guedrbootamp6.R
import io.keepcoding.guedrbootamp6.fragment.ForecastFragment
import io.keepcoding.guedrbootamp6.model.Cities
import kotlinx.android.synthetic.main.activity_city_page.*

class CityPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_page)

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
}
