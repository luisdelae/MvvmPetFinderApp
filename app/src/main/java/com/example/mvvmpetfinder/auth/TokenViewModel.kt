package com.example.mvvmpetfinder.auth

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.Token
import com.example.mvvmpetfinder.data.source.TokenRepository

class TokenViewModel(application: Application): AndroidViewModel(application) {
    var tokenLiveData = MutableLiveData<Token>()

    private var mvvmPetFinderRepository:  TokenRepository? = null

    init {
        mvvmPetFinderRepository =  TokenRepository()
        getToken()
    }

    private fun getToken() {
        mvvmPetFinderRepository?.let {
            tokenLiveData = it.getToken()
        }

        tokenLiveData.observeForever { token ->
            val privateMode = 0
            val prefName = "ACCESS_TOKEN"
            val prefKey = "TOKEN"
            val sharedPref: SharedPreferences = getApplication<Application>().getSharedPreferences(prefName, privateMode)
            sharedPref.edit().putString(prefKey, token.accessToken).apply()

            val storedToken = sharedPref.getString(prefKey, "nothing")
            Log.d("TokenViewModel", "storedToken: $storedToken")
        }
    }
}