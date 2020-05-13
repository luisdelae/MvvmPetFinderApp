package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class ContactInfo(
    @JsonProperty("email")
    val email: String? = "",
    @JsonProperty("phone")
    val phone: String? = "",
    @JsonProperty("address")
    val address: Address?
)