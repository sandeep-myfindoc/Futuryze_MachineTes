package com.futuryze.service

import com.futuryze.model.topRateMoviesList.ServerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/onecall")
    fun getHourlyTemp(@Query("lat") lat: String,@Query("lon") lon: String,@Query("appid") apiKey: String, @Query("page") page: Long): Call<ServerResponse?>
}