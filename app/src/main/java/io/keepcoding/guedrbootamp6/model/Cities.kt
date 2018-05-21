package io.keepcoding.guedrbootamp6.model

import io.keepcoding.guedrbootamp6.R

// object para declarar como singleton
object Cities {
    private val cities: List<City> = listOf(
            City("Madrid", listOf(
                    Forecast(25f, 10f, 35f, "Soleado con alguna nube 1", R.drawable.ico_02),
                    Forecast(26f, 11f, 36f, "Soleado con alguna nube 2", R.drawable.ico_03),
                    Forecast(27f, 12f, 37f, "Soleado con alguna nube 3", R.drawable.ico_04),
                    Forecast(28f, 13f, 38f, "Soleado con alguna nube 4", R.drawable.ico_09),
                    Forecast(29f, 14f, 39f, "Soleado con alguna nube 5", R.drawable.ico_10)
            )),
            City("Jaén", listOf(
                    Forecast(36f, 23f, 19f, "Sol a tope 1", R.drawable.ico_01),
                    Forecast(37f, 24f, 20f, "Sol a tope 2", R.drawable.ico_02),
                    Forecast(38f, 25f, 21f, "Sol a tope 3", R.drawable.ico_03),
                    Forecast(39f, 26f, 22f, "Sol a tope 4", R.drawable.ico_04),
                    Forecast(40f, 27f, 23f, "Sol a tope 5", R.drawable.ico_09)
            )),
            City("Quito", listOf(
                    Forecast(30f, 15f, 40f, "Lluvia 1", R.drawable.ico_10),
                    Forecast(31f, 16f, 41f, "Lluvia 2", R.drawable.ico_11),
                    Forecast(32f, 17f, 42f, "Lluvia 3", R.drawable.ico_13),
                    Forecast(33f, 18f, 43f, "Lluvia 4", R.drawable.ico_50),
                    Forecast(34f, 19f, 44f, "Lluvia 5", R.drawable.ico_01)
            ))
    )

    val count
        get() = cities.size

    fun getCity(index: Int) = cities[index]

    operator fun get(index: Int) = cities[index]

    fun toArray() = cities.toTypedArray()
}