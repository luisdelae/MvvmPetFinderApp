package com.example.mvvmpetfinder.data.request

data class PetRequest(
    val type: String? = null,
    // Can have multiple breeds. Must be comma separated.
    val breed: String? = null,
    // Can have multiple breeds. Must be comma separated.
    val size: String? = null,
    // Can have multiple breeds. Must be comma separated.
    val gender: String? = null,
    // Can have multiple breeds. Must be comma separated.
    val age: String? = null,
    // Can have multiple breeds. Must be comma separated.
    val color: String? = null,
    // Can have multiple breeds. Must be comma separated.
    val coat: String? = null,
    // Can have multiple breeds. Must be comma separated.
    val name: String? = null,
    val goodWithChildren: Boolean? = null,
    val goodWithDogs: Boolean? = null,
    val goodWithCats: Boolean? = null,
    // Can have multiple breeds. Must be comma separated.
    val location: String? = null,
    // Can have multiple breeds. Must be comma separated.
    val distance: Int? = null,
    val page: Int? = null,
    // Default 20 results per page
    val limitPerPage: Int? = null
)