package io.keepcoding.guedrbootamp6.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import io.keepcoding.guedrbootamp6.R
import io.keepcoding.guedrbootamp6.fragment.CityPagerFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_city_pager)

        //toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val initialCityIndex = intent.getIntExtra(EXTRA_CITY, 0)

        // Creo, si hace falta, el CityPagerFragment pasándole la ciudad inicial
        if (supportFragmentManager.findFragmentById(R.id.view_pager_fragment) == null) {
            val fragment = CityPagerFragment.newInstance(initialCityIndex)
            supportFragmentManager.beginTransaction()
                    .add(R.id.view_pager_fragment, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> { // han llamado a la flecha de back
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
