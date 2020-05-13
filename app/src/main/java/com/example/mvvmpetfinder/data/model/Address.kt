package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class Address(
    @JsonProperty("address1")
    val addressLine1: String? = "",
    @JsonProperty("address2")
    val addressLine2: String? = "",
    @JsonProperty("city")
    val city: String? = "",
    @JsonProperty("state")
    val state: String? = "",
    @JsonProperty("postcode")
    val postcode: String? = "",
    @JsonProperty("country")
    val country: String? = ""
)