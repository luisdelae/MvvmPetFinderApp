package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class GoodWith(
    @SerializedName("children")
    val children: Boolean,
    @SerializedName("dogs")
    val dogs: Boolean,
    @SerializedName("cats")
    val cats: Boolean
)