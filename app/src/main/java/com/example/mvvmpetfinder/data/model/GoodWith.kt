package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class GoodWith(
    @JsonProperty("children")
    val children: Boolean,
    @JsonProperty("dogs")
    val dogs: Boolean,
    @JsonProperty("cats")
    val cats: Boolean
)