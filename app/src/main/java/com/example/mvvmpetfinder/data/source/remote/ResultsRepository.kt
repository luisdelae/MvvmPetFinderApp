package com.example.mvvmpetfinder.data.source.remote

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.Pets
import com.example.mvvmpetfinder.data.request.PetRequest
import com.example.mvvmpetfinder.data.source.RetrofitService
import com.example.mvvmpetfinder.util.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.QueryMap

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
            petFinderApi = RetrofitService().createAuthService(PetFinderApi::class.java, token)
        }
    }

    fun getPets(petRequest: PetRequest): MutableLiveData<Pets> {
        val petsLiveData = MutableLiveData<Pets>()

        /**
         *  Convert request to [HashMap] for the [PetFinderApi.getPets] [QueryMap]
         */
        val gson = Gson()
        val json = gson.toJson(petRequest)
        val petRequestData: Map<String, Any> = gson.fromJson(json, object : TypeToken<HashMap<String, Any>>() {}.type)

        petFinderApi?.getPets(petRequestData)?.enqueue(object : Callback<Pets> {
            override fun onFailure(call: Call<Pets>, t: Throwable) {
                Log.e("ResultsRepository", "error $t")            }

            override fun onResponse(call: Call<Pets>, response: Response<Pets>) {
                if (response.isSuccessful) {
                    petsLiveData.value = response.body()
                } else {
                    Log.e("SearchRepository", "Unsuccessful. See above for details")
                }
            }
        })

        return petsLiveData
    }
}