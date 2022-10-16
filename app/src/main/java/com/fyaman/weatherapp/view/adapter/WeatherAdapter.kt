package com.fyaman.weatherapp.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fyaman.weatherapp.R
import com.fyaman.weatherapp.data.model.Daily
import com.fyaman.weatherapp.data.model.OneCall
import com.fyaman.weatherapp.data.utils.Extensions
import com.fyaman.weatherapp.data.utils.Extensions.getFormattedDayString
import com.fyaman.weatherapp.data.utils.Extensions.getFormattedMonthString
import com.fyaman.weatherapp.databinding.ActivityMainBinding.bind
import com.fyaman.weatherapp.databinding.WeatherRowBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonDisposableHandle.parent
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class WeatherAdapter(
    //private val WeatherList: MutableList<OneCall>
    private val WeatherList: List<Daily>
)
    : ListAdapter<OneCall, WeatherAdapter.WeatherViewHolder>(WeatherDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType:  Int): WeatherViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_row,parent,false)
        return WeatherViewHolder(view)
    }
    class WeatherViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val tvRowDay = view.findViewById<TextView>(R.id.tvRowDay)
        private val ivRowWeather = view.findViewById<ImageView>(R.id.ivRowWeather)
        private val tvRowWindSpeed = view.findViewById<TextView>(R.id.tvRowWindSpeed)
        private val tvRowCelcius = view.findViewById<TextView>(R.id.tvRowCelcius)

        fun bind(daily: Daily){
            val icon = daily.weather[0].icon
            println(icon)
            Picasso.get().load("http://openweathermap.org/img/wn/$icon@2x.png").into(ivRowWeather)
            val windSpeed = daily.wind_speed.toString()
            tvRowWindSpeed.text = "Wind: $windSpeed km/h"
            tvRowDay.text = daily.dt.getFormattedDayString()
            val lowestTemp = daily.temp.min.toString().take(2)
            val highestTemp = daily.temp.max.toString().take(2)
            tvRowCelcius.text = "$lowestTemp° / $highestTemp°"
        }
    }
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(WeatherList[position])
    }
    override fun getItemCount(): Int {
        return WeatherList.size
    }
    class WeatherDiffUtil : DiffUtil.ItemCallback<OneCall>(){
        override fun areItemsTheSame(oldItem: OneCall, newItem: OneCall): Boolean {

            return oldItem.daily[0].dt == newItem.daily[0].dt

        }

        override fun areContentsTheSame(oldItem: OneCall, newItem: OneCall): Boolean {
            return oldItem == newItem
        }
    }



    interface OneCallListener {
        fun onClicked(OneCall: OneCall)
    }




}


