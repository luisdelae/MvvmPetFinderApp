package com.example.mvvmpetfinder.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.Token
import com.example.mvvmpetfinder.data.source.remote.TokenApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MvvmPetFinderRepository {

    private var tokenApi = RetrofitService().createNoAuthService(TokenApi::class.java)

    fun getToken(): MutableLiveData<Token> {
        val tokenLiveData = MutableLiveData<Token>()

        // Extract these and put em somewhere safe
        tokenApi.getToken(
                "client_credentials",
                "ZeaqbkOd8saaRetT54TeP8FZxkjGcPVnSIguMQJYpOJ6rULlFZ",
                "IljBAjxgfkQLDgkcEnSQtxWi7efhErxgiflQ8srA"
        ).enqueue(object : Callback<Token>{
            override fun onResponse(call: Call<Token>, r: Response<Token>) {
                if (r.isSuccessful) {
                    tokenLiveData.value = r.body()
                } else {
                    Log.e("MvvmPetFinderRepository", "Unsuccessful. See above for details")
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.e("MvvmPetFinderRepository", "error $t")
            }
        })

        return tokenLiveData
    }
}