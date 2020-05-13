package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class PetColors(
    @JsonProperty("primary")
    val primary: String?,
    @JsonProperty("secondary")
    val secondary: String?,
    @JsonProperty("tertiary")
    val tertiary: String?
)