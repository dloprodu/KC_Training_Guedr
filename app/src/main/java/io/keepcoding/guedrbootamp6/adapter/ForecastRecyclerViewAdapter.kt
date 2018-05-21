package io.keepcoding.guedrbootamp6.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import io.keepcoding.guedrbootamp6.model.Forecast
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.keepcoding.guedrbootamp6.R
import io.keepcoding.guedrbootamp6.getTemperatureUnits
import io.keepcoding.guedrbootamp6.model.TemperatureUnit
import io.keepcoding.guedrbootamp6.units2String
import io.keepcoding.guedrbootamp6.forecastDay

class ForecastRecyclerViewAdapter(private val forescast: List<Forecast>)
    : RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_forecast, parent, false)

        view.setOnClickListener(onClickListener)

        return  ForecastViewHolder(view)
    }

    override fun getItemCount() = forescast.count()

    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        holder?.bindForecast(forescast[position], getTemperatureUnits(holder.context), position)
    }

    inner class ForecastViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        val forecastDay = itemsView.findViewById<TextView?>(R.id.forecast_day)
        val forecastImage = itemsView.findViewById<ImageView?>(R.id.forecast_image)
        val maxTemp = itemView.findViewById<TextView?>(R.id.max_temp)
        val minTemp = itemView.findViewById<TextView?>(R.id.min_temp)
        val humidity = itemView.findViewById<TextView?>(R.id.humidity)
        val forecastDescription = itemView.findViewById<TextView?>(R.id.forecast_description)
        val context = itemsView.context


        fun bindForecast(forecast: Forecast, temperatureUnit: TemperatureUnit, day: Int) {
            // Actualizamos la vista con el modelo
            forecastDay?.text = forecastDay(day)

            forecastImage?.setImageResource(forecast.icon)
            forecastDescription?.text = forecast.description

            updateTemperatureView(forecast, temperatureUnit)

            humidity?.text = context.getString(R.string.humidity_temp_format, forecast.humidity)
        }

        // Aquí actualizaremos la interfaz con las temperaturas
        fun updateTemperatureView(forecast: Forecast, temperatureUnit: TemperatureUnit) {
            val unitsString = units2String(temperatureUnit)
            maxTemp?.text = context.getString(R.string.max_temp_format, forecast.getMaxTemp(temperatureUnit), unitsString)
            minTemp?.text = context.getString(R.string.min_temp_format, forecast.getMinTemp(temperatureUnit), unitsString)
        }
    }
}