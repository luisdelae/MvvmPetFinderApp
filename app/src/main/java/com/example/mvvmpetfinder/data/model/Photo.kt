package com.example.mvvmpetfinder.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
class Photo(
    @JsonProperty("small")
    val small: String,
    @JsonProperty("medium")
    val medium: String,
    @JsonProperty("large")
    val large: String,
    @JsonProperty("full")
    val full: String
) : Parcelable