package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Pets(
    @JsonProperty("animals")
    val pets: ArrayList<Pet>,
    @JsonProperty("pagination")
    val paginationInfo: Pagination
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Pet(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("organization_id")
    val organizationId: String? = "",
    @JsonProperty("url")
    val url: String? = "",
    @JsonProperty("type")
    val type: String? = "",
    @JsonProperty("species")
    val species: String? = "",
    @JsonProperty("breeds")
    val breeds: Breeds,
    @JsonProperty("colors")
    val colors: PetColors,
    @JsonProperty("age")
    val age: String? = "",
    @JsonProperty("gender")
    val gender: String? = "",
    @JsonProperty("size")
    val size: String? = "",
    @JsonProperty("coat")
    val coat: String? = "",
    @JsonProperty("name")
    val name: String? = "",
    @JsonProperty("description")
    val description: String? = "",
    @JsonProperty("photos")
    val photos: List<Photo>?,
    @JsonProperty("videos")
    val videos: List<Video>?,
    @JsonProperty("status")
    val status: String? = "",
    @JsonProperty("attributes")
    val attributes: Attributes?,
    @JsonProperty("environment")
    val goodWith: GoodWith?,
    @JsonProperty("tags")
    val tags: List<String>?,
    @JsonProperty("contact")
    val contactInfo: ContactInfo?,
    // ISO8601 format
    @JsonProperty("published_at")
    val publishedDate: String? = "",
    @JsonProperty("distance")
    val distance: Float? = 0F
)