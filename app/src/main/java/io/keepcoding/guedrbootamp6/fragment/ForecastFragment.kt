package io.keepcoding.guedrbootamp6.fragment

import android.app.Activity
import android.support.v4.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import io.keepcoding.guedrbootamp6.R
import io.keepcoding.guedrbootamp6.activity.SettingsActivity
import io.keepcoding.guedrbootamp6.adapter.ForecastRecyclerViewAdapter
import io.keepcoding.guedrbootamp6.model.City
import io.keepcoding.guedrbootamp6.model.Forecast
import io.keepcoding.guedrbootamp6.model.TemperatureUnit
import kotlinx.android.synthetic.main.content_forecast.*
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastFragment: Fragment() {

    companion object {
        val ARG_CITY = "ARG_CITY"

        fun newInstance(city: City): Fragment {
            val fragment = ForecastFragment()

            val arguments = Bundle()
            arguments.putSerializable(ARG_CITY, city)

            fragment.arguments = arguments

            return fragment
        }
    }

    private enum class VIEW_INDEX(val index: Int) {
        LOADING(0), FORECAST(1)
    }

    val REQUEST_SETTINGS = 1
    val PREFERENCE_UNITS = "UNITS"

    //var forecastImage: ImageView? = null
    //lateinit var forecastImage: ImageView
    //val forecastImage by lazy {
    //    findViewById<ImageView>(R.id.forecastImage)
    //}

    var forecast: List<Forecast>? = null
        set(value) {
            field = value

            if (value != null) {
                forecast_list.adapter = ForecastRecyclerViewAdapter(value)

            }
        }

    val units: TemperatureUnit
        get() = when(PreferenceManager.getDefaultSharedPreferences(activity).getInt(PREFERENCE_UNITS, TemperatureUnit.CELSIUS.ordinal)) {
            TemperatureUnit.CELSIUS.ordinal -> TemperatureUnit.CELSIUS
            else                            -> TemperatureUnit.FAHRENHEIT
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater?.inflate(R.layout.fragment_forecast, container, false)
        return root!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // le decimos al ViewSwitcher que muestre la primera vista
        view_switcher.setInAnimation(activity, android.R.anim.fade_in)
        view_switcher.setOutAnimation(activity, android.R.anim.fade_out)
        view_switcher.displayedChild = VIEW_INDEX.LOADING.index;

        view?.postDelayed({
            // Aqui simulamos que ya nos hemos bajado la información del tiempo

            // Configuramos el RecycleView.
            // - Primero decimos como se visualizan sus elementos
            forecast_list.layoutManager = LinearLayoutManager(activity)

            // - Le decimos quien es el que anima al RecyclerView
            forecast_list.itemAnimator = DefaultItemAnimator()

            // - Por último tenemos que decirle los datos que van a rellenar el RecyclerView. Eso
            //   es tarea del setter del forecast

            val city: City = arguments?.getSerializable(ARG_CITY) as City
            forecast = city.forecast

            view_switcher.displayedChild = VIEW_INDEX.FORECAST.index
        }, resources.getInteger(R.integer.default_delay).toLong())
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.activity_forecast, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_show_settings -> {
                startActivityForResult(
                        SettingsActivity.intent(activity, units),
                        REQUEST_SETTINGS )

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_SETTINGS -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Volvemos de settings con datos sobre las unidades elegidas por el usuario
                    val newUnits = data.getSerializableExtra(SettingsActivity.EXTRA_UNITS) as TemperatureUnit
                    val oldUnit = units

                    // Guardamos las preferencias del usuario
                    PreferenceManager.getDefaultSharedPreferences(activity)
                            .edit()
                            .putInt(PREFERENCE_UNITS, newUnits.ordinal)
                            .apply()

                    // Actualizo la interfaz con las nuevas unidades
                    updateTemperatureView()

                    val newUnitsString = if (newUnits == TemperatureUnit.CELSIUS) getString(R.string.user_selects_celsius)
                    else getString(R.string.user_select_fahrenhiet)

                    // Toast.makeText(this, newUnitsString, Toast.LENGTH_LONG).show()
                    Snackbar.make(view!!, newUnitsString, Snackbar.LENGTH_LONG)
                            .setAction("Deshacer" ) {
                                PreferenceManager.getDefaultSharedPreferences(activity)
                                        .edit()
                                        .putInt(PREFERENCE_UNITS, oldUnit.ordinal)
                                        .apply()

                                updateTemperatureView()
                            }
                            .show()
                }
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser && forecast != null) {
            updateTemperatureView()
        }
    }

    fun updateTemperatureView() {
        if (forecast != null) {
            forecast_list.adapter = ForecastRecyclerViewAdapter(forecast!!)
        }
    }
}