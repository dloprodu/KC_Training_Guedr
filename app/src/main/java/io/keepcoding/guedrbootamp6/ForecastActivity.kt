package io.keepcoding.guedrbootamp6

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_forecast.*

class ForecastActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val TAG = ForecastActivity::class.java.canonicalName
    }

    //var forecastImage: ImageView? = null
    //lateinit var forecastImage: ImageView
    //val forecastImage by lazy {
    //    findViewById<ImageView>(R.id.forecastImage)
    //}

    var forecast: Forecast? = null
        set(value) {
            if (value != null) {
                forecast_image.setImageResource(value.icon)
                forecast_description.text = value.description

                max_temp.text = getString(R.string.max_temp_format, value.maxTemp)
                min_temp.text = getString(R.string.min_temp_format, value.minTemp)
                humidity.text = getString(R.string.humidity_temp_format, value.humidity)
            }
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

    override fun onClick(v: View?) {
        /*
        val image = when (v?.id) {
            R.id.european_system_button -> {
                Log.v(TAG, "European Button clicked")
                R.drawable.offline_weather
            }
            R.id.american_system_button -> {
                Log.v(TAG, "American Button clicked")
                R.drawable.offline_weather2
            }
            else -> {
                Log.v(TAG, "Unknown click")
                R.drawable.offline_weather
            }
        }

        forecastImage?.setImageResource(image)
        */
    }
}
