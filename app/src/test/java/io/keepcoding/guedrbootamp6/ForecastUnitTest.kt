package io.keepcoding.guedrbootamp6

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before


class ForecastUnitTest {
    lateinit var forecast: Forecast

    @Before
    fun setUp() {
        forecast = Forecast(
                25f,
                10f,
                35f,
                "Soleado con alguna nube",
                R.drawable.ico_01)
    }

    @Test
    fun maxTempUnitsConversion_isCorrect() {
        assertEquals(77f, forecast.getMaxTemp(TemperatureUnit.FAHRENHEIT))
    }

    @Test
    fun minTempUnitsConversion_isCorrect() {
        assertEquals(50f, forecast.getMinTemp(TemperatureUnit.FAHRENHEIT))
    }

    @Test(expected = IllegalArgumentException::class)
    fun humidityOverRange_ThrowsArgumentException() {
        Forecast(25f, 10f, 100.01f, "Sol", R.drawable.ico_01)
    }

    @Test(expected = IllegalArgumentException::class)
    fun humidityUnderRange_ThrowsArgumentException() {
        Forecast(25f, 10f, -1f, "Sol", R.drawable.ico_01)
    }
}