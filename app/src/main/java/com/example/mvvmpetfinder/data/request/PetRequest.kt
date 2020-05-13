package com.example.mvvmpetfinder.data.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

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

val mapper = jacksonObjectMapper()

// Convert a Map to an object
inline fun <reified T> Map<String, Any>.toObject(): T {
    return convert()
}

// Convert an object to a Map
fun <T> T.toMap(): Map<String, Any> {
    return convert()
}

// Convert an object of type T to type R
inline fun <T, reified R> T.convert(): R {
    val json = mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(this)
    return mapper.readValue(json, R::class.java)
}