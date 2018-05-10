package io.keepcoding.guedrbootamp6

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_UNITS = "EXTRA_UNITS"

        fun intent(context: Context, units: TemperatureUnit): Intent {
            val intent = Intent(context, SettingsActivity::class.java)

            intent.putExtra(EXTRA_UNITS, units)

            return intent
        }
    }

    val initialUnits by lazy {
        intent.getSerializableExtra(EXTRA_UNITS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        ok_btn.setOnClickListener { acceptSettings() }
        cancel_btn.setOnClickListener { cancelSettings() }

        if (initialUnits == TemperatureUnit.CELSIUS.ordinal) {
            units_rg.check(R.id.cesius_rb)
        } else {
            units_rg.check(R.id.farenheit_rb)
        }
    }

    private fun cancelSettings() {
        finish()
    }

    private fun acceptSettings() {
        finish()
    }
}
