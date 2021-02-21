package com.futuryze.model.topRateMoviesList

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*


data class HourlyTemperatureModel constructor(@SerializedName("dt") val dt : Int,
                                              @SerializedName("temp") val temp : Double,
                                              @SerializedName("feels_like") val feels_like : Double,
                                              @SerializedName("pressure") val pressure : Int,
                                              @SerializedName("humidity") val humidity : Int,
                                              @SerializedName("dew_point") val dew_point : Double,
                                              @SerializedName("clouds") val clouds : Int,
                                              @SerializedName("visibility") val visibility : Int,
                                              @SerializedName("wind_speed") val wind_speed : Double,
                                              @SerializedName("wind_deg") val wind_deg : Int,
                                              @SerializedName("pop") val pop : Int)
{

    companion object{
        @JvmStatic @BindingAdapter("dt")
        fun loadDate(imageView: TextView, dt: String?) {
            val timeStamp =
                java.lang.Long.valueOf(dt) * 1000 // its need to be in milisecond
            val dateFormat = Date(timeStamp)
            val dateInString =
                SimpleDateFormat("dd/MMM/yyyy hh:mma", Locale.getDefault())
                    .format(dateFormat)
            imageView.setText("Date: "+dateInString);
        }

    }




}