package com.futuryze.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.futuryze.model.topRateMoviesList.HourlyTemperatureModel
import com.futuryze.repositories.PlayerDataSourceFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

//JsonObject
class HourlyTempListViewModel(application: Application) : AndroidViewModel(application) {
    val topRatedMoviePagedList: LiveData<PagedList<HourlyTemperatureModel>>
    private val executor: Executor

    init {
        val factory = PlayerDataSourceFactory()
        //liveDataSource = factory.getMutableLiveData();
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .build()
        executor = Executors.newFixedThreadPool(5)
        topRatedMoviePagedList = LivePagedListBuilder(factory, config)
            .setFetchExecutor(executor)
            .build()
    }
}