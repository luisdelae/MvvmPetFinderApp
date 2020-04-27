package com.example.mvvmpetfinder.results

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
import androidx.navigation.fragment.navArgs
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.data.request.PetRequest

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ResultsFragment : Fragment() {

    val args: ResultsFragmentArgs by navArgs()
    var resultsViewModel: ResultsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        resultsViewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)
        resultsViewModel?.getPets(PetRequest(petType = args.petType))

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultsViewModel?.petsLiveData?.observe(viewLifecycleOwner, Observer {pets ->
            Log.d("ResultsFragment", "pets count: ${pets.pets.count()}")
        })

        // From here we should probably just go to the single animal page.
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_ResultsFragment_to_SearchFragment)
        }
    }
}
