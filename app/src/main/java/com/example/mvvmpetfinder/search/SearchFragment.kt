package com.example.mvvmpetfinder.search

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.util.Constants.Companion.SPINNER_PET_TYPES
import com.example.mvvmpetfinder.util.Constants.Companion.SPINNER_SELECTED_VALUE
import com.example.mvvmpetfinder.util.Constants.Companion.ZIP_CODE_SELECTED_VALUE
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

/**
 * Fragment used to search for pets by type and zip code
 */
class SearchFragment : Fragment() {

    var searchViewModel: SearchViewModel? = null
    var selectedPetType: String = ""
    var zipCode: String = ""

    private lateinit var currentView: View
    private lateinit var spinner: Spinner
    private lateinit var zipCodeEditText: TextInputEditText
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

        currentView = view

        spinner = currentView.findViewById(R.id.pet_type_spinner)

        zipCodeEditText = currentView.findViewById(R.id.zip_code_edit_text)

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

    /**
     * Loads up the spinner with data
     */
    private fun loadPetTypesSpinner(petTypeNames: List<String>) {

        this.context?.let {
            val arrayAdapter = ArrayAdapter<String>(it,
                android.R.layout.simple_spinner_item, petTypeNames)

            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Can't really happen here)
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedPetType = petTypeNames[position]
                }
            }
        }

        initSearchButtonClick()
        initEnterListener()
    }

    /**
     * Initializes the button on click event
     */
    private fun initSearchButtonClick() {
        val button: Button = currentView.findViewById(R.id.search_button)
        button.setOnClickListener {
            showResults()
        }
    }

    /**
     * Initializes keyboard listener for enter button
     */
    private fun initEnterListener() {
        zipCodeEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
                showResults()
                closeKeyboard()
                true
            } else false
        }
    }

    /**
     * Handles showing the results page
     */
    private fun showResults() {
        zipCode = zipCodeEditText.text.toString()

        if (!zipError(zipCode)) {
            val action = SearchFragmentDirections
                .actionSearchFragmentToResultsFragment(
                    petType = selectedPetType,
                    petTypeList = petTypeNames.toTypedArray(),
                    zipCode = zipCode
                )
            findNavController().navigate(action)
        }
    }

    /**
     * Error handling for the zip code input
     */
    private fun zipError(zip: String): Boolean {
        val zipLayout = currentView.findViewById<TextInputLayout>(R.id.zip_code_layout)

        return if (zip.length < 5) {
            zipLayout.isErrorEnabled = true
            zipLayout.error = "Please enter a 5 digit zip code"
            true
        } else {
            zipLayout.error = null
            zipLayout.isErrorEnabled = false
            false
        }
    }

    /**
     * Closes out the keyboard
     */
    private fun closeKeyboard() {
        val imm = this.requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentView.windowToken, 0)
    }
}
