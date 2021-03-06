package com.example.mvvmpetfinder.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Parcelize
class Breeds(
    @JsonProperty("primary")
    val primary: String?,
    @JsonProperty("secondary")
    val secondary: String?,
    @JsonProperty("mixed")
    val mixed: Boolean,
    @JsonProperty("unknown")
    val unknown: Boolean
) : Parcelable