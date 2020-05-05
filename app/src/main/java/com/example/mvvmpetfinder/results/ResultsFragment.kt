package com.example.mvvmpetfinder.results

import android.nfc.tech.MifareUltralight.PAGE_SIZE
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
import com.example.mvvmpetfinder.data.model.Pets
import com.example.mvvmpetfinder.data.request.PetRequest


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ResultsFragment : Fragment() {

    private val args: ResultsFragmentArgs by navArgs()
    lateinit var resultsViewModel: ResultsViewModel

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ResultsAdapter
    val layoutManager = LinearLayoutManager(this.context)

    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        resultsViewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)
        resultsViewModel.getPets(PetRequest(type = args.petType, page = FIRST_PAGE))

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultsViewModel.petsLiveData?.observe(viewLifecycleOwner, Observer { pets ->
//            pets.pets.forEach {
//                Timber.d("pet gson tree: ${ Gson().toJsonTree(it) }")
//            }

            if (pets.pets.isNotEmpty()) {
                initRecyclerView(view, pets)
            } else {
                // TODO: Show some results not found view
            }
        })
    }

    // TODO: Add scroll listener for pagination
    private fun initRecyclerView(view: View, pets: Pets) {
        recyclerView = view.findViewById(R.id.results_recyclerview)

        recyclerView.layoutManager = layoutManager

        adapter = ResultsAdapter(pets.pets)

        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE
                    ) {
                        loadMorePets()
                    }
                }
            }
        })
    }

    private fun loadMorePets() {

    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val FIRST_PAGE = 1
    }
}
