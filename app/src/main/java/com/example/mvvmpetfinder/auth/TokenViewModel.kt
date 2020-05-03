package com.example.mvvmpetfinder.auth

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.Token
import com.example.mvvmpetfinder.data.source.TokenRepository
import com.example.mvvmpetfinder.util.Constants.Companion.EMPTY_STRING
import com.example.mvvmpetfinder.util.Constants.Companion.SHARED_PREF_AUTH_TOKEN
import com.example.mvvmpetfinder.util.Constants.Companion.SHARED_PREF_NAME
import com.example.mvvmpetfinder.util.Constants.Companion.SHARED_PREF_PRIVATE_MODE
import timber.log.Timber

class TokenViewModel(application: Application): AndroidViewModel(application) {
    var tokenLiveData = MutableLiveData<Token>()

    private var tokenRepository: TokenRepository? = null

    init {
        tokenRepository =  TokenRepository()
        getToken()
    }

    private fun getToken() {
        tokenRepository?.let {
            tokenLiveData = it.getToken()
        }

        tokenLiveData.observeForever { token ->
            val sharedPref: SharedPreferences =
                getApplication<Application>()
                    .getSharedPreferences(SHARED_PREF_NAME, SHARED_PREF_PRIVATE_MODE)
            sharedPref.edit().putString(SHARED_PREF_AUTH_TOKEN, token.accessToken).apply()

            val storedToken = sharedPref.getString(SHARED_PREF_AUTH_TOKEN, EMPTY_STRING)
            Timber.d("storedToken: $storedToken")
        }
    }
}