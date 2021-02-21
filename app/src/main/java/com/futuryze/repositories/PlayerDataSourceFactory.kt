package com.futuryze.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.futuryze.model.topRateMoviesList.HourlyTemperatureModel

class PlayerDataSourceFactory : DataSource.Factory<Long, HourlyTemperatureModel>() {
    var playerLiveDataSource: MutableLiveData<PlayerDataSource>
    /*override fun create(): PlayerDataSource {
        val playerDataSource = PlayerDataSource()
        playerLiveDataSource.postValue(playerDataSource)
        return playerDataSource
        //return mutableLiveData
    }*/

    init {
        playerLiveDataSource = MutableLiveData()
    }

    override fun create(): DataSource<Long, HourlyTemperatureModel?> {
        var playerDataSource = PlayerDataSource()
        playerLiveDataSource.postValue(playerDataSource)
        return playerDataSource
    }
}