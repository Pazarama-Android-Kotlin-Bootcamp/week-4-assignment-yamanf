package com.fyaman.weatherapp.data.utils

import com.fyaman.weatherapp.R
import com.squareup.picasso.Picasso

object ImageChanger {
    var iconLocal : Int? = null
    fun iconChanger (icon: String) : Int {
        if (icon == "01d" ){
            iconLocal = R.drawable.day_sun
        }else if (icon=="02d"||icon == "03d"||icon=="04d"){
            iconLocal = R.drawable.day_clouds
        }else if (icon=="09d"||icon == "10d"){
            iconLocal = R.drawable.day_rain
        }else if (icon=="11d"){
            iconLocal = R.drawable.day_storm
        }else if (icon=="13d"){
            iconLocal = R.drawable.day_snow
        }else if (icon == "01n" ){
            iconLocal = R.drawable.night_moon
        }else if (icon=="02n"||icon == "03n"||icon=="04n"){
            iconLocal = R.drawable.night_clouds
        }else if (icon=="09n"||icon == "10n"){
            iconLocal = R.drawable.night_rain
        }else if (icon=="11n"){
            iconLocal = R.drawable.night_storm
        }else if (icon=="13n"){
            iconLocal = R.drawable.night_snow
        }else{
            iconLocal = R.drawable.day_sun
        }
        return iconLocal!!
    }
}