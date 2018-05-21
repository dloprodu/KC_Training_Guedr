package io.keepcoding.guedrbootamp6

import io.keepcoding.guedrbootamp6.model.TemperatureUnit

fun units2String(units: TemperatureUnit) = if (units == TemperatureUnit.CELSIUS) "ºC"
else "F"

fun forecastDay(index: Int) = when(index) {
    0 -> "Hoy"
    1 -> "Mañana"
    2 -> "Pasado mañana"
    3 -> "Pasado pasado mañana"
    4 -> "Pasado pasado pasado mañana"
    else -> "Dia de la marmota"
}