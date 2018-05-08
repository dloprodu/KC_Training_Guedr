package io.keepcoding.guedrbootamp6

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = MainActivity::class.java.canonicalName
    //var forecastImage: ImageView? = null
    //lateinit var forecastImage: ImageView

    val forecastImage by lazy {
        findViewById<ImageView>(R.id.forecastImage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val numero = savedInstanceState?.getInt("Numero")

        // val europeanButton = findViewById(R.id.european_system_button) as? Button
        // val americanButton = findViewById(R.id.american_system_button) as? Button
        // val europeanButton = findViewById<Button>(R.id.european_system_button)
        // var europeanButton: Button = findViewById(R.id.european_system_button)
        // forecastImage = findViewById(R.id.forecastImage) as? ImageView

        //europeanButton?.setOnClickListener(this)
        //americanButton?.setOnClickListener(this)

        /*
        europeanButton?.setOnClickListener {
            forecastImage?.setImageResource(R.drawable.offline_weather)
        }

        americanButton?.setOnClickListener {
            forecastImage?.setImageResource(R.drawable.offline_weather2)
        }
        */

        // synthetic view binding (kotlin extensions)
        european_system_button.setOnClickListener {
            forecastImage?.setImageResource(R.drawable.offline_weather)
        }

        american_system_button.setOnClickListener {
            forecastImage?.setImageResource(R.drawable.offline_weather2)
        }

        Log.v(TAG,"Han llamado a onCreate" )
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt("Numero", 3)
    }

    override fun onClick(v: View?) {
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
    }
}
