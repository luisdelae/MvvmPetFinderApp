package com.example.mvvmpetfinder.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.data.request.PetRequest
import com.google.gson.Gson
import timber.log.Timber

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
        resultsViewModel?.getPets(PetRequest(type = args.petType))

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultsViewModel?.petsLiveData?.observe(viewLifecycleOwner, Observer { pets ->
//            pets.pets.forEach {
//                Timber.d("pet gson tree: ${ Gson().toJsonTree(it) }")
//            }

            if (pets.pets.isNotEmpty()) {
                val recyclerView = view.findViewById<RecyclerView>(R.id.results_recyclerview)

                val adapter = ResultsAdapter(pets.pets)

                recyclerView.layoutManager = LinearLayoutManager(this.context)

                recyclerView.adapter = adapter
            } else {
                // TODO: Show some results not found view
            }
        })
    }
}
