package com.example.mvvmpetfinder.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Pagination(
    @JsonProperty("count_per_page")
    val countPerPage: Int,
    @JsonProperty("total_count")
    val totalCount: Int,
    @JsonProperty("current_page")
    val currentPage: Int,
    @JsonProperty("total_pages")
    val totalPages: Int
)