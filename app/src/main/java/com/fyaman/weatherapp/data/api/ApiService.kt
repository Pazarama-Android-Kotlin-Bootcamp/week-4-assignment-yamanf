package com.fyaman.weatherapp.data.api
import retrofit2.http.GET
import retrofit2.http.Query
import com.fyaman.weatherapp.data.model.OneCall
import retrofit2.Call

interface ApiService {
    @GET("onecall")
    fun getOneCallDaily(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("exclude") exclude: String
    ) : Call<OneCall>

}