package com.futuryze.view.welcome

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import com.futuryze.R
import com.futuryze.util.PermissionUtils
import com.futuryze.view.HourlyTempListView.HourlyTempListActivity
import com.futuryze.view.HourlyTempListView.SuperActivity
import com.google.android.gms.location.*
import com.willyweathermachinetest.sharedpreferences.Prefs
import com.willyweathermachinetest.sharedpreferences.SharedPreferencesName

class WelcomeActivity : SuperActivity() {
    private val LOCATION_PERMISSION_REQUEST_CODE: Int=101
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationProviderClient:FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        locationRequest = LocationRequest().setInterval(2000)
            .setFastestInterval(2000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object :LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    Prefs.with(this@WelcomeActivity)?.save(SharedPreferencesName.LATITUDE,location.latitude)
                    Prefs.with(this@WelcomeActivity)?.save(SharedPreferencesName.LONGITUDE,location.longitude)
                    startActivity(Intent(this@WelcomeActivity,HourlyTempListActivity::class.java))
                    finish()
                }
            }
        };
        updateToolbarWithoutBackButton(resources.getString(R.string.title_welcome_screen))
    }

    override fun onStart() {
        super.onStart()
        when {
            PermissionUtils.isAccessFineLocationGranted(this) -> {
                when {
                    PermissionUtils.isLocationEnabled(this) -> {
                        setUpLocationListener()
                    }
                    else -> {
                        PermissionUtils.showGPSNotEnabledDialog(this)
                    }
                }
            }
            else -> {
                PermissionUtils.requestAccessFineLocationPermission(
                    this,
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun setUpLocationListener() {
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }

    override fun onPause() {
        super.onPause()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    when {
                        PermissionUtils.isLocationEnabled(this) -> {
                            setUpLocationListener()
                        }
                        else -> {
                            PermissionUtils.showGPSNotEnabledDialog(this)
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.location_permission_not_granted),
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }
            }
        }
    }
}
