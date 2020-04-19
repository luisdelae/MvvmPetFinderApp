package com.example.mvvmpetfinder.data.source.remote

import com.example.mvvmpetfinder.data.model.Token
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenApi {
    @FormUrlEncoded
    @POST("oauth2/token")
    fun getToken(@Field("grant_type") grantType: String,
                 @Field("client_id") apiKey: String,
                 @Field("client_credentials") apiSecret: String)
            : Call<Token>
}