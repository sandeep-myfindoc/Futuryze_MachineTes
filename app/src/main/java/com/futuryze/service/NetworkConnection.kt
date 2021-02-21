package com.futuryze.service

import com.futuryze.BuildConfig


class NetworkConnection {
    var baseUrl: String? = null

    init {
        if (BuildConfig.DEBUG) {
            baseUrl = BuildConfig.baseUrl
        } else {
            baseUrl = BuildConfig.baseUrl
        }
    }
}