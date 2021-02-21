package com.futuryze.view

import android.app.Application
import com.futuryze.broadcastreceiver.ConnectivityReceiver


class FuturyzeApplication : Application() {
  override fun onCreate() {
      super.onCreate()
      instance = this
  }
    fun setConnectivityListener(listener: ConnectivityReceiver.ConnectivityReceiverListener?) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }
    companion object {
        @get:Synchronized
        var instance: FuturyzeApplication? = null

            private set
    }
}