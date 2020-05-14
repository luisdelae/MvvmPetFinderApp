package com.example.mvvmpetfinder.data.source.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.Token
import com.example.mvvmpetfinder.data.source.RetrofitService
import com.example.mvvmpetfinder.data.source.remote.TokenApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class TokenRepository {

    private var tokenApi = RetrofitService()
        .createNoAuthService(TokenApi::class.java)

    fun getToken(): MutableLiveData<Token> {
        val tokenLiveData = MutableLiveData<Token>()

        // Extract these and put em somewhere safe
        tokenApi.getToken(
                "client_credentials",
                "ZeaqbkOd8saaRetT54TeP8FZxkjGcPVnSIguMQJYpOJ6rULlFZ",
                "IljBAjxgfkQLDgkcEnSQtxWi7efhErxgiflQ8srA"
        ).enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, r: Response<Token>) {
                if (r.isSuccessful) {
                    tokenLiveData.value = r.body()
                } else {
                    Timber.e("Unsuccessful. See above for details")
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Timber.e("error $t")
            }
        })

        return tokenLiveData
    }
}