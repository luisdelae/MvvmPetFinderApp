package com.example.mvvmpetfinder.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.util.Constants.Companion.SPINNER_PET_TYPES
import com.example.mvvmpetfinder.util.Constants.Companion.SPINNER_SELECTED_VALUE
import com.example.mvvmpetfinder.util.Constants.Companion.ZIP_CODE_SELECTED_VALUE
import java.util.*

/**
 * Shows a simple search screen to begin the search process
 */
class SearchFragment : Fragment() {

    var searchViewModel: SearchViewModel? = null
    var selectedPetType: String = ""
    var zipCode: String = ""

    private lateinit var spinner: Spinner
    private lateinit var zipCodeEditText: EditText
    private val petTypeNames = mutableListOf<String>()

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

        spinner = view.findViewById(R.id.pet_type_spinner)

        zipCodeEditText = view.findViewById(R.id.zip_code)

        // Used when orientation changes, mainly
        savedInstanceState?.let {
            if (it.containsKey(SPINNER_PET_TYPES)) {
                it.getStringArrayList(SPINNER_PET_TYPES)?.let { types ->
                    petTypeNames.addAll(types.toList())
                }

                loadPetTypesSpinner(petTypeNames)
            }

            if (it.containsKey(SPINNER_SELECTED_VALUE)) {
                spinner.setSelection(petTypeNames.indexOf(it.getString(SPINNER_SELECTED_VALUE)))
            }

            if (it.containsKey(ZIP_CODE_SELECTED_VALUE)) {
                zipCodeEditText.setText(it.getString(ZIP_CODE_SELECTED_VALUE))
            }
        }

        if (savedInstanceState == null && petTypeNames.isNotEmpty()) {
            loadPetTypesSpinner(petTypeNames)
        }

        if (petTypeNames.isEmpty()) {
            searchViewModel?.petTypesLiveData?.observe(viewLifecycleOwner, Observer { listOfPetTypes ->

                listOfPetTypes.forEach {
                    petTypeNames.add(it.name)
                }

                loadPetTypesSpinner(petTypeNames)
            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putStringArrayList(SPINNER_PET_TYPES, petTypeNames as ArrayList<String>)
        outState.putString(SPINNER_SELECTED_VALUE, selectedPetType)
        outState.putString(ZIP_CODE_SELECTED_VALUE, zipCode)
    }

    private fun loadPetTypesSpinner(petTypeNames: List<String>) {

        this.context?.let {
            val arrayAdapter = ArrayAdapter<String>(it,
                android.R.layout.simple_spinner_item, petTypeNames)

            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    enableSearchButton(false)
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedPetType = petTypeNames[position]
                    enableSearchButton(true)
                }
            }
        }

        initSearchButtonClick()
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

            zipCode = zipCodeEditText.text.toString()

            val action = SearchFragmentDirections
                .actionSearchFragmentToResultsFragment(
                    petType = selectedPetType,
                    petTypeList = petTypeNames.toTypedArray(),
                    zipCode = zipCode
                )
            findNavController().navigate(action)
        }
    }
}
