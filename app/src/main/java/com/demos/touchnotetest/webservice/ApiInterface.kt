package com.demos.touchnotetest.webservice

import com.demos.touchnotetest.models.WeatherResponseDO

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    // https://api.darksky.net/forecast/166c199fe045e51a136adb2262e1fd86/37.8267,-122.4233

    @GET("forecast/{apikey}/{latlang}")
    fun getWeatherReport(@Path(value = "apikey", encoded = true) apikey: String,
                         @Path(value = "latlang", encoded = true) latlang: String): Call<WeatherResponseDO>

}

