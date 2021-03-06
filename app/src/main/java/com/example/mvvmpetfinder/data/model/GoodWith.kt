package com.example.mvvmpetfinder.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Parcelize
class GoodWith(
    @JsonProperty("children")
    val children: Boolean,
    @JsonProperty("dogs")
    val dogs: Boolean,
    @JsonProperty("cats")
    val cats: Boolean
) : Parcelable