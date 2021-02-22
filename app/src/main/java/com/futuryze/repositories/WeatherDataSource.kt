package com.futuryze.repositories

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.futuryze.BuildConfig
import com.futuryze.model.topRateMoviesList.HourlyTemperatureModel
import com.futuryze.model.topRateMoviesList.ServerResponse
import com.futuryze.service.ApiService
import com.futuryze.service.NetworkConnection
import com.futuryze.service.RestClient

import retrofit2.Call
import retrofit2.Callback


// It act as a Repositry
class WeatherDataSource : PageKeyedDataSource<Long, HourlyTemperatureModel?>() {
    private var service: ApiService? = null
    private val limit = 20
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, HourlyTemperatureModel?>) {
        Log.d("Intial Load", "Intial Load")
        //FuturyzeApplication.instance?.let { Prefs.with(it) }!!.getFloat(SharedPreferencesName.LATITUDE,0F)
        service = RestClient(NetworkConnection()).client
        service?.getHourlyTemp(31.6340F,74.8723F,BuildConfig.apiKey, 1)?.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(call: Call<ServerResponse?>, serverResponse: retrofit2.Response<ServerResponse?>) {
                Log.d("Inside Success","Inside Success"+serverResponse.body()?.hourly.toString());
                if (serverResponse != null && serverResponse.body() != null) {
                    val hourlyTempList: List<HourlyTemperatureModel?>? = serverResponse?.body()?.hourly
                    callback.onResult(hourlyTempList!!, null, 2.toLong())
                }
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                Log.d("Inside Failure",t.message+"");
            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, HourlyTemperatureModel?>) {
        Log.d("Load Before", "Load Before")
        service = RestClient(NetworkConnection()).client
        Log.d("Param key", params.key.toString() + "")
        service?.getHourlyTemp(31.6340F,74.8723F,BuildConfig.apiKey,params.key)?.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(call: Call<ServerResponse?>, serverResponse: retrofit2.Response<ServerResponse?>) {
                val hourlyTempList: List<HourlyTemperatureModel?>? = serverResponse?.body()?.hourly
                if (serverResponse != null && serverResponse.body() != null) {
                    val hourlyTempList: List<HourlyTemperatureModel?>? = serverResponse?.body()?.hourly
                    val key: Long
                    key = if (params.key > 1) {
                        params.key - 1
                    } else {
                        0
                    }
                    callback.onResult(hourlyTempList!!, key)
                }
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {}
        })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, HourlyTemperatureModel?>) {
        Log.d("Load After", "Load After")
        service = RestClient(NetworkConnection()).client
        Log.d("Param key", params.key.toString() + "")
        service?.getHourlyTemp(31.6340F,74.8723F,BuildConfig.apiKey,params.key)?.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(call: Call<ServerResponse?>, serverResponse: retrofit2.Response<ServerResponse?>) {
                val hourlyTempList: List<HourlyTemperatureModel?>? = serverResponse?.body()?.hourly
                if (serverResponse != null && serverResponse.body() != null) {
                    val hourlyTempList: List<HourlyTemperatureModel?>? = serverResponse?.body()?.hourly
                    callback.onResult(hourlyTempList!!, params.key + 1)
                }
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {}
        })
    }
}