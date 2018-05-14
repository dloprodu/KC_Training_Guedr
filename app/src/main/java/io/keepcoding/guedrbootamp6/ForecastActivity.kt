package io.keepcoding.guedrbootamp6

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_forecast.*

class ForecastActivity : AppCompatActivity() {

    companion object {
        val TAG = ForecastActivity::class.java.canonicalName
    }

    val REQUEST_SETTINGS = 1

    val PREFERENCE_UNITS = "UNITS"

    //var forecastImage: ImageView? = null
    //lateinit var forecastImage: ImageView
    //val forecastImage by lazy {
    //    findViewById<ImageView>(R.id.forecastImage)
    //}

    var forecast: Forecast? = null
        set(value) {
            field = value

            if (value != null) {
                forecast_image.setImageResource(value.icon)
                forecast_description.text = value.description

                updateTemperatureView()

                humidity.text = getString(R.string.humidity_temp_format, value.humidity)
            }
        }

    val units: TemperatureUnit
        get() = when(PreferenceManager.getDefaultSharedPreferences(this).getInt(PREFERENCE_UNITS, TemperatureUnit.CELSIUS.ordinal)) {
            TemperatureUnit.CELSIUS.ordinal -> TemperatureUnit.CELSIUS
            else                            -> TemperatureUnit.FAHRENHEIT
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        // Restore state
        // ------------------------------------------

        // val numero = savedInstanceState?.getInt("Numero")


        // findViewById
        // ------------------------------------------

        /*
        val europeanButton = findViewById(R.id.european_system_button) as? Button
        val americanButton = findViewById(R.id.american_system_button) as? Button
        // val europeanButton = findViewById<Button>(R.id.european_system_button)
        // var europeanButton: Button = findViewById(R.id.european_system_button)
        // forecastImage = findViewById(R.id.forecastImage) as? ImageView

        //europeanButton?.setOnClickListener(this)
        //americanButton?.setOnClickListener(this)

        europeanButton?.setOnClickListener {
            forecastImage?.setImageResource(R.drawable.offline_weather)
        }

        americanButton?.setOnClickListener {
            forecastImage?.setImageResource(R.drawable.offline_weather2)
        }
        */

        // Synthetic view binding (kotlin extensions)
        // ------------------------------------------

        /*
        european_system_button.setOnClickListener {
            forecastImage?.setImageResource(R.drawable.offline_weather)
        }

        american_system_button.setOnClickListener {
            forecastImage?.setImageResource(R.drawable.offline_weather2)
        }
        */

        forecast = Forecast(
                25f,
                10f,
                35f,
                "Soleado con alguna nube",
                R.drawable.ico_01)

        Log.v(TAG,"Han llamado a onCreate" )
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt("Numero", 3)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_forecast, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_show_settings -> {
                startActivityForResult(
                    SettingsActivity.intent(this, units),
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
                    PreferenceManager.getDefaultSharedPreferences(this)
                            .edit()
                            .putInt(PREFERENCE_UNITS, newUnits.ordinal)
                            .apply()

                    // Actualizo la interfaz con las nuevas unidades
                    updateTemperatureView()

                    val newUnitsString = if (newUnits == TemperatureUnit.CELSIUS) getString(R.string.user_selects_celsius)
                        else getString(R.string.user_select_fahrenhiet)

                    // Toast.makeText(this, newUnitsString, Toast.LENGTH_LONG).show()
                    Snackbar.make(findViewById<View>(android.R.id.content), newUnitsString, Snackbar.LENGTH_LONG)
                            .setAction("Deshacer" ) {
                                PreferenceManager.getDefaultSharedPreferences(this)
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

    // Aquí actualizaremos la interfaz con las temperaturas
    fun updateTemperatureView() {
        val unitsString = units2String()
        max_temp.text = getString(R.string.max_temp_format, forecast?.getMaxTemp(units), unitsString)
        min_temp.text = getString(R.string.min_temp_format, forecast?.getMinTemp(units), unitsString)
    }

    fun units2String() = if (units == TemperatureUnit.CELSIUS) "ºC"
    else "F"

}
