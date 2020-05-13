package com.example.mvvmpetfinder.results

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.Pets
import com.example.mvvmpetfinder.data.request.PetRequest
import com.example.mvvmpetfinder.data.source.remote.ResultsRepository

class ResultsViewModel(application: Application): AndroidViewModel(application) {
    var initialPetsLiveData = MutableLiveData<Pets>()
    var morePetsLiveData = MutableLiveData<Pets>()

    private var resultsRepository: ResultsRepository? = null

    init {
        resultsRepository = ResultsRepository(application)
    }

    fun getPetsInitial(petRequest: PetRequest) {
        resultsRepository?.let {
            initialPetsLiveData = it.getPets(petRequest)
        }
    }

    fun getMorePets(petRequest: PetRequest) {
        resultsRepository?.let {
            morePetsLiveData = it.getPets(petRequest)
        }
    }
}