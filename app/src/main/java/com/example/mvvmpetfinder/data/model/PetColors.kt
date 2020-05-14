package com.example.mvvmpetfinder.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
class PetColors(
    @JsonProperty("primary")
    val primary: String?,
    @JsonProperty("secondary")
    val secondary: String?,
    @JsonProperty("tertiary")
    val tertiary: String?
) : Parcelable