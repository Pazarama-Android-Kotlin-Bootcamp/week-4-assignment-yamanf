package com.fyaman.weatherapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyaman.weatherapp.R
import com.fyaman.weatherapp.data.api.ApiClient
import com.fyaman.weatherapp.data.model.OneCall
import com.fyaman.weatherapp.data.utils.Extensions.getFormattedDateString
import com.fyaman.weatherapp.data.utils.Extensions.getFormattedDayString
import com.fyaman.weatherapp.data.utils.Extensions.getFormattedMonthString
import com.fyaman.weatherapp.data.utils.ImageChanger.iconChanger
import com.fyaman.weatherapp.databinding.FragmentMainBinding
import com.fyaman.weatherapp.view.adapter.WeatherAdapter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment()   {
    private lateinit var rvWeather : RecyclerView
    private var fragmentMainBinding: FragmentMainBinding? = null
    private var rvWeatherAdapter : WeatherAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding
        rvWeather = view.findViewById(R.id.rvWeather)
        rvWeather.layoutManager = LinearLayoutManager(context)
        getWeather()
    }

    private fun getWeather(){
        ApiClient.getApiService().getOneCallDaily(37.60,26.12,"metric","minutely,alerts").enqueue(object : Callback<OneCall> {
            override fun onResponse(call: Call<OneCall>, response: Response<OneCall>) {
                Log.d("WeatherFragment", response.body().toString())
                if (response.isSuccessful){
                    val onecall = response.body()
                    onecall?.let{ it ->

                        //List Initialization
                        val listDaily = it.daily
                        val listCurrent = it.current

                        //TimeZone
                        fragmentMainBinding?.tvTimeZone?.text = it.timezone


                        //WeatherBigIcon
                        val icon = listCurrent.weather[0].icon
                        val iconChanged = iconChanger(icon) // This method changes icon from api to local ones
                        val ivIcon = fragmentMainBinding?.iconBig
                        ivIcon?.setImageResource(iconChanged)

                        //Today Temperature
                        val celcius = listCurrent.temp.toString().take(2)
                        fragmentMainBinding?.tvCelcius?.text = "$celcius°"
                        //Today's Description
                        fragmentMainBinding?.tvDescription?.text = listCurrent.weather[0].description.replaceFirstChar { it.uppercase() }
                        //Date Row
                        fragmentMainBinding?.tvDay?.text = listCurrent.dt.getFormattedDayString()
                        val month = listCurrent.dt.getFormattedMonthString()
                        val dayOfMonth = listCurrent.dt.getFormattedDateString()
                        fragmentMainBinding?.tvDate?.text = "$month $dayOfMonth"

                        //Wind Speed
                        val windSpeed = listCurrent.wind_speed
                        fragmentMainBinding?.tvWindSpeed?.text = " Wind: $windSpeed km/h"

                        //Humidity
                        val humidity = listCurrent.humidity
                        fragmentMainBinding?.tvHumidity?.text = "Humidity: $humidity %"

                        //Adapter binding
                        val adapter = WeatherAdapter(listDaily)
                        rvWeatherAdapter = adapter
                        rvWeatherAdapter?.apply {
                            submitList(listOf(it))
                            setupAdapter(adapter)
                        }

                        Log.d("WaterFragment", it.daily[0].weather[0].description.toString() )
                    }
                }
            }

            override fun onFailure(call: Call<OneCall>, t: Throwable) {
                Log.d("WaterFragment", t.toString())
            }

        })

    }
    private fun setupAdapter(adapter: WeatherAdapter){
        fragmentMainBinding?.rvWeather?.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }


}