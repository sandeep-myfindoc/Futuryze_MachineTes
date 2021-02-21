package com.futuryze.model.topRateMoviesList

import com.google.gson.annotations.SerializedName

data class ServerResponse constructor(@SerializedName("lat") val lat : Double,
                                      @SerializedName("lon") val lon : Double,
                                      @SerializedName("timezone") val timezone : String,
                                      @SerializedName("timezone_offset") val timezone_offset : Int,
                                      @SerializedName("hourly") val hourly : List<HourlyTemperatureModel>)
{


}