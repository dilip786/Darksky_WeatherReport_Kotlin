package com.demos.touchnotetest.presenters

import android.content.Context
import android.text.TextUtils
import com.demos.touchnotetest.models.WeatherErrorResponseDO
import com.demos.touchnotetest.models.WeatherResponseDO
import com.demos.touchnotetest.utils.Utils
import com.demos.touchnotetest.webservice.ApiClient
import com.demos.touchnotetest.webservice.ApiInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPresenter(var context: Context, var view: WeatherPresenterInterface) {


    fun getWeatherInfo(countryName: String, apikey: String, latitude: Double, longitude: Double) {

        var utils = Utils();


        if (TextUtils.isEmpty(apikey))
            view.onFailure("Please pass a valid API key.")
        else if (!utils.validateCoordinates(latitude, longitude))
            view.onFailure("Please pass valid coordinates")
        else if (!utils.isNetworkConnected(context))
            view.onFailure("Please check your network connectivity & Try again")
        else {

            val apiService = ApiClient.client!!.create(ApiInterface::class.java)
            val call = apiService.getWeatherReport(apikey, "$latitude,$longitude")
            call.enqueue(object : Callback<WeatherResponseDO> {
                override fun onResponse(call: Call<WeatherResponseDO>, response: Response<WeatherResponseDO>) {

                    // Success case
                    if (response.isSuccessful)
                        view.onSuccess(countryName, response.body())
                    else {
                        val gson = Gson()
                        val errorResponse = gson.fromJson(response.errorBody().charStream(), WeatherErrorResponseDO::class.java)

                        if (errorResponse.getCode() == 403 || errorResponse.getCode() == 400) {
                            // Invalid API KEY case
                            view.onFailure(errorResponse.error)
                        } else {
                            // Other cases
                            view.onFailure("Something went wrong please try again")
                        }
                    }// Failure case
                }

                override fun onFailure(call: Call<WeatherResponseDO>, t: Throwable) {
                    view.onFailure(t.toString())
                }
            })
        }
    }

    interface WeatherPresenterInterface {

        fun onSuccess(countryName: String, nearbyResponseDO: WeatherResponseDO)

        fun onFailure(message: String?)
    }
}



