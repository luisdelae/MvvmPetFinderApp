package com.example.mvvmpetfinder.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.PetType
import com.example.mvvmpetfinder.data.source.repository.SearchRepository

class SearchViewModel(application: Application): AndroidViewModel(application) {

    var petTypesLiveData = MutableLiveData<List<PetType>>()

    private var searchRepository: SearchRepository? = null

    init {
        searchRepository =
            SearchRepository(
                application
            )
        getPetTypes()
    }

    private fun getPetTypes() {
        searchRepository?.let { searchRepo ->
            petTypesLiveData = searchRepo.getPetTypes()
        }

//        val sharedPref: SharedPreferences =
//            getApplication<Application>().getSharedPreferences(
//                    Constants.SHARED_PREF_NAME,
//                    Constants.SHARED_PREF_PRIVATE_MODE)

//        val existingPetTypes = sharedPref.getStringSet(SHARED_PREF_PET_TYPES, mutableSetOf<String>())
//
//        existingPetTypes?.let { existingTypes ->
//            if (existingTypes.isEmpty()) {
//                searchRepository?.let { searchRepo ->
//                    petTypesLiveData = searchRepo.getPetTypes()
//                }
//
//                petTypesLiveData.observeForever { petTypes ->
//                    val remotePetTypes = mutableSetOf<String>()
//                    petTypes.forEach {
//                        remotePetTypes.add(it.name)
//                    }
//                    sharedPref.edit().putStringSet(SHARED_PREF_PET_TYPES, remotePetTypes).apply()
//                }
//            }
//        }
    }
}