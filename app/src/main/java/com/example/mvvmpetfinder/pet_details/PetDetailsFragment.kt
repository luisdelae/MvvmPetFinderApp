package com.example.mvvmpetfinder.pet_details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mvvmpetfinder.R

// TODO: Build the actual fragment view
// TODO: Seem to have all data needed. Do I though? If so, no repository needed. Delete it.
class PetDetailsFragment : Fragment() {

    private lateinit var viewModel: PetDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pet_details_fragment, container, false)
    }


}
