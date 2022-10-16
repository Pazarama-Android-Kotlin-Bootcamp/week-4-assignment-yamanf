package com.fyaman.weatherapp.data.api

import com.fyaman.weatherapp.BuildConfig
import com.fyaman.weatherapp.data.api.interceptor.AuthInterceptor
import com.fyaman.weatherapp.data.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiClient {

    companion object{
        private lateinit var apiService: ApiService

        fun getApiService(): ApiService{
            if (!::apiService.isInitialized){
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService
        }

        private fun getHttpClient(): OkHttpClient{
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor())
            httpClient.connectTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
            httpClient.readTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
            httpClient.writeTimeout(90,java.util.concurrent.TimeUnit.SECONDS)
            return httpClient.build()

        }
    }
}

