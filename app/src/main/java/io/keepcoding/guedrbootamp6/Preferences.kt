package io.keepcoding.guedrbootamp6

import android.content.Context
import android.preference.PreferenceManager
import io.keepcoding.guedrbootamp6.model.TemperatureUnit


val PREFERENCE_UNITS = "UNITS"

fun getTemperatureUnits(context: Context) = if (PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(PREFERENCE_UNITS, TemperatureUnit.CELSIUS.ordinal) == TemperatureUnit.CELSIUS.ordinal)
    TemperatureUnit.CELSIUS else TemperatureUnit.FAHRENHEIT

fun setTemperatureUnits(context: Context, newUnits: TemperatureUnit) {
    // Guardamos las preferencias del usuario
    PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putInt(PREFERENCE_UNITS, newUnits.ordinal)
            .apply()
}