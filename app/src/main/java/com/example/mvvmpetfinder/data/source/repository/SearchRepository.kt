package com.example.mvvmpetfinder.data.source.repository

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.PetType
import com.example.mvvmpetfinder.data.model.PetTypes
import com.example.mvvmpetfinder.data.source.RetrofitService
import com.example.mvvmpetfinder.data.source.remote.PetFinderApi
import com.example.mvvmpetfinder.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class SearchRepository(appContext: Application) {
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

    fun getPetTypes(): MutableLiveData<List<PetType>> {
        val petTypesLiveData = MutableLiveData<List<PetType>>()

        petFinderApi?.getTypes()?.enqueue(object : Callback<PetTypes> {
            override fun onResponse(call: Call<PetTypes>, r: Response<PetTypes>) {
                if (r.isSuccessful) {
                    petTypesLiveData.value = r.body()?.types
                } else {
                    Timber.e("Unsuccessful. See above for details")
                }
            }

            override fun onFailure(call: Call<PetTypes>, t: Throwable) {
                Timber.e("error $t")
            }
        })

        return petTypesLiveData
    }
}