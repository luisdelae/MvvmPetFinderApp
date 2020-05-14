package com.example.mvvmpetfinder.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
class Video(
    @JsonProperty("embed")
    val embededVideo: String
) : Parcelable