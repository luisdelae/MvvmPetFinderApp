package com.example.mvvmpetfinder.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Parcelize
class Attributes(
    @JsonProperty("spayed_neutered")
    val spayedNeutered: Boolean,
    @JsonProperty("house_trained")
    val houseTrained: Boolean,
    @JsonProperty("declawed")
    val declawed: Boolean,
    @JsonProperty("special_needs")
    val specialNeeds: Boolean,
    @JsonProperty("shots_current")
    val shortsCurrent: Boolean
) : Parcelable