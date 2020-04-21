package com.example.mvvmpetfinder.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmpetfinder.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchFragment : Fragment() {

    var searchViewModel: SearchViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        searchViewModel?.petTypesLiveData?.observe(viewLifecycleOwner, Observer {
            Log.d("SearchFragment", "first type name: ${it.first().name}")
        })

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun loadPetTypes() {

    }

    private fun loadBreeds() {

    }

    private fun loadColors() {

    }

    private fun loadSizes() {

    }

    private fun loadGenders() {

    }

    private fun loadAge() {

    }

    private fun loadCoat() {

    }

    private fun loadGoodWith() {

    }
}
