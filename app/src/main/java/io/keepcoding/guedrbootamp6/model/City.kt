package io.keepcoding.guedrbootamp6.model

import java.io.Serializable

data class City(var name: String, var forecast: List<Forecast>): Serializable {
    override fun toString(): String = name
}