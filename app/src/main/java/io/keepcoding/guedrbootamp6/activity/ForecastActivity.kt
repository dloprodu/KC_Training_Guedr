package io.keepcoding.guedrbootamp6.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.ViewGroup
import io.keepcoding.guedrbootamp6.R
import io.keepcoding.guedrbootamp6.fragment.CityListFragment
import io.keepcoding.guedrbootamp6.fragment.CityPagerFragment
import io.keepcoding.guedrbootamp6.model.City

class ForecastActivity : AppCompatActivity(), CityListFragment.OnCitySelectedListener {

    companion object {
        val TAG = ForecastActivity::class.java.canonicalName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        // Averiguamos qué interfaz hemos cargado
        // Eso lo averiguamos preguntando si en la interfaz tenermos un FrameLayout concreto
        if (findViewById<ViewGroup>(R.id.city_list_fragment) != null) {
            // Hemos cargado una interfaz que tiene el hueco para el fragment CityLilstFragment
            // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
            if (supportFragmentManager.findFragmentById(R.id.city_list_fragment)  == null) {
                val fragment: CityListFragment = CityListFragment.newInstance()

                // Añadir dinámicamente el fragment a la interfaz
                supportFragmentManager.beginTransaction()
                        .add(R.id.city_list_fragment, fragment)
                        .commit()
            }
        }

        if (findViewById<ViewGroup>(R.id.view_pager_fragment) != null) {
            // Hemos cargado una interfaz que tiene el hueco para el fragment CityPagerFragment
            if (supportFragmentManager.findFragmentById(R.id.view_pager_fragment) == null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.view_pager_fragment, CityPagerFragment.newInstance(0))
                        .commit()
            }
        }

        // Log.v(TAG,"Han llamado a onCreate" )
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt("Numero", 3)
    }

    override fun onCitySelected(city: City, position: Int) {
        val cityPagerFragment = supportFragmentManager.findFragmentById(R.id.view_pager_fragment) as? CityPagerFragment

        if (cityPagerFragment != null) {
            cityPagerFragment.moveToCity(position)
        } else {
            val intent = CityPagerActivity.intent(this, position)
            startActivity(intent)
        }
    }
}
