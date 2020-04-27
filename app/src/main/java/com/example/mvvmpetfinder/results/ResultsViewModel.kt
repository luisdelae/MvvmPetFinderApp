package com.example.mvvmpetfinder.results

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpetfinder.data.model.Pets
import com.example.mvvmpetfinder.data.request.PetRequest
import com.example.mvvmpetfinder.data.source.remote.ResultsRepository

class ResultsViewModel(application: Application): AndroidViewModel(application) {
    var petsLiveData = MutableLiveData<Pets>()

    private var resultsRepository: ResultsRepository? = null

    init {
        resultsRepository = ResultsRepository(application)
    }

    fun getPets(petRequest: PetRequest) {
        resultsRepository?.let {
            petsLiveData = it.getPets(petRequest)
        }
    }
}