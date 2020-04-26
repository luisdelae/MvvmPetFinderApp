package com.example.mvvmpetfinder.data.model

import com.google.gson.annotations.SerializedName

class Pagination(
    @SerializedName("count_per_page")
    val countPerPage: Int,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)