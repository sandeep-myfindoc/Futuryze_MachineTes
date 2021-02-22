package com.futuryze.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.futuryze.model.topRateMoviesList.HourlyTemperatureModel

class WeatherDataSourceFactory : DataSource.Factory<Long, HourlyTemperatureModel>() {
    var weatherLiveDataSource: MutableLiveData<WeatherDataSource>
    /*override fun create(): PlayerDataSource {
        val playerDataSource = PlayerDataSource()
        playerLiveDataSource.postValue(playerDataSource)
        return playerDataSource
        //return mutableLiveData
    }*/

    init {
        weatherLiveDataSource = MutableLiveData()
    }

    override fun create(): DataSource<Long, HourlyTemperatureModel?> {
        var playerDataSource = WeatherDataSource()
        weatherLiveDataSource.postValue(playerDataSource)
        return playerDataSource
    }
}