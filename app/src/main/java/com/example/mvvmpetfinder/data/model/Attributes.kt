package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class Attributes(
    @SerializedName("spayed_neutered")
    val spayedNeutered: Boolean,
    @SerializedName("house_trained")
    val houseTrained: Boolean,
    @SerializedName("declawed")
    val declawed: Boolean,
    @SerializedName("special_needs")
    val specialNeeds: Boolean,
    @SerializedName("shots_current")
    val shortsCurrent: Boolean
)