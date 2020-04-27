package com.example.mvvmpetfinder.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmpetfinder.R

/**
 * Shows a simple search screen to begin the search process
 */
class SearchFragment : Fragment() {

    var searchViewModel: SearchViewModel? = null
    var selectedPetType: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel?.petTypesLiveData?.observe(viewLifecycleOwner, Observer { listOfPetTypes ->
            val petTypeNames = mutableListOf<String>()

            listOfPetTypes.forEach {
                petTypeNames.add(it.name)
            }

            loadPetTypesSpinner(petTypeNames)
            initSearchButtonClick()
        })
    }

    private fun loadPetTypesSpinner(petTypeNames: List<String>) {
        val spinner: Spinner? = view?.findViewById(R.id.pet_type_spinner)

        this.context?.let {
            val arrayAdapter = ArrayAdapter<String>(it,
                android.R.layout.simple_spinner_item, petTypeNames)

            spinner?.adapter = arrayAdapter

            spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    enableSearchButton(false)
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedPetType = petTypeNames[position]
                    enableSearchButton(true)
                }
            }
        }
    }

    // Disable/Enable search button when a selection is made
    private fun enableSearchButton(enable: Boolean) {
        val button: Button? = view?.findViewById(R.id.search_button)
        button?.isClickable = enable
        button?.isEnabled = enable
    }

    private fun initSearchButtonClick() {
        val button: Button? = view?.findViewById(R.id.search_button)
        button?.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToResultsFragment(selectedPetType)
            findNavController().navigate(action)
        }
    }
}
