package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class Pets(
    @SerializedName("animals")
    val pets: ArrayList<Pet>,
    @SerializedName("pagination")
    val paginationInfo: Pagination
)

class Pet(
    @SerializedName("id")
    val id: Int,
    @SerializedName("organization_id")
    val organizationId: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("breeds")
    val breeds: Breeds,
    @SerializedName("colors")
    val colors: PetColors,
    @SerializedName("age")
    val age: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("coat")
    val coat: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("photos")
    val photos: List<Photo>,
    @SerializedName("videos")
    val videos: List<Video>,
    @SerializedName("status")
    val status: String,
    @SerializedName("attributes")
    val attributes: Attributes,
    @SerializedName("environment")
    val goodWith: GoodWith,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("contact")
    val contactInfo: ContactInfo,
    @SerializedName("published_at")
    val publishedDate: String,
    @SerializedName("distance")
    val distance: Float
)