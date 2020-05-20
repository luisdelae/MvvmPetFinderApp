package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

class PetTypes(
    @JsonProperty("types")
    val types: List<PetType>
)

@JsonIgnoreProperties(ignoreUnknown = true)
class PetType(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("coats")
    val coats: List<String>,
    @JsonProperty("colors")
    val colors: List<String>,
    @JsonProperty("genders")
    val genders: List<String>
)