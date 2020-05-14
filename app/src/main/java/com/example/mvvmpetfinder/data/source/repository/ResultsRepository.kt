package com.example.mvvmpetfinder.data.source.repository

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.Pets
import com.example.mvvmpetfinder.data.request.PetRequest
import com.example.mvvmpetfinder.data.request.toMap
import com.example.mvvmpetfinder.data.source.RetrofitService
import com.example.mvvmpetfinder.data.source.remote.PetFinderApi
import com.example.mvvmpetfinder.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.QueryMap
import timber.log.Timber

class ResultsRepository(appContext: Application) {
    private var petFinderApi: PetFinderApi? = null

    init {
        val sharedPref: SharedPreferences =
            appContext.getSharedPreferences(
                Constants.SHARED_PREF_NAME,
                Constants.SHARED_PREF_PRIVATE_MODE
            )
        val token = sharedPref.getString(Constants.SHARED_PREF_AUTH_TOKEN, Constants.EMPTY_STRING)

        token?.let {
            petFinderApi = RetrofitService()
                .createAuthService(PetFinderApi::class.java, token)
        }
    }

    fun getPets(petRequest: PetRequest): MutableLiveData<Pets> {
        val petsLiveData = MutableLiveData<Pets>()

        /**
         *  Convert request to [HashMap] for the [PetFinderApi.getPets] [QueryMap]
         *  Gson builder override forces [Double] to return as [Long] so that we may send the
         *  correct data type for the [PetRequest]
         */
        val petRequestData: Map<String, Any> = petRequest.toMap()

        petFinderApi?.getPets(petRequestData)?.enqueue(object : Callback<Pets> {
            override fun onFailure(call: Call<Pets>, t: Throwable) {
                Timber.e("error $t")            }

            override fun onResponse(call: Call<Pets>, response: Response<Pets>) {
                if (response.isSuccessful) {
                    petsLiveData.value = response.body()
                } else {
                    Timber.e("Unsuccessful. See above for details")
                }
            }
        })

        return petsLiveData
    }
}