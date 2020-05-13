package com.example.mvvmpetfinder.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.data.model.Pet
import com.example.mvvmpetfinder.data.request.PetRequest
import com.example.mvvmpetfinder.util.Constants
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ResultsFragment : Fragment() {

    private val args: ResultsFragmentArgs by navArgs()
    private lateinit var resultsViewModel: ResultsViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ResultsAdapter
    val layoutManager = LinearLayoutManager(this.context)
    private val petTypeNames = mutableListOf<String>()
    private val petsList = mutableListOf<Pet>()

    private lateinit var spinner: Spinner
    private var selectedPetType: String = ""

    private var isLoading = false
    private var isLastPage = false
    private var currentPage = FIRST_PAGE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        selectedPetType = args.petType
        petTypeNames.addAll(args.petTypeList)

        resultsViewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)

        setLoading(true)
        resultsViewModel.getPetsInitial(PetRequest(type = args.petType, page = currentPage))

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = view.findViewById(R.id.pet_type_spinner)

        loadPetTypesSpinner(petTypeNames)

        spinner.setSelection(petTypeNames.indexOf(selectedPetType))

        resultsViewModel.initialPetsLiveData.observe(viewLifecycleOwner, Observer { pets ->
            setCurrentPage(pets.paginationInfo.currentPage, pets.paginationInfo.totalPages)

            setLoading(false)

            if (pets.pets.isNotEmpty()) {
                petsList.addAll(pets.pets)
                initRecyclerView(view, petsList)
            } else {
                showEmptyResults()
            }
        })
    }

    private fun loadPetTypesSpinner(petTypeNames: List<String>) {

        this.context?.let {
            val arrayAdapter = ArrayAdapter<String>(it,
                android.R.layout.simple_spinner_item, petTypeNames)

            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    enableSearchButton(false)
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedPetType = petTypeNames[position]
//                    enableSearchButton(true)
                }
            }
        }

//        initSearchButtonClick()
    }

    private fun initScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE
                    ) {
                        Timber.d("Loading more pets.")
                        loadMorePets()
                    }
                }
            }
        }
    }

    private fun initRecyclerView(view: View, pets: List<Pet>) {
        recyclerView = view.findViewById(R.id.results_recyclerview)

        recyclerView.layoutManager = layoutManager

        adapter = ResultsAdapter(pets)

        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(initScrollListener())
    }

    private fun loadMorePets() {
        setLoading(true)

        resultsViewModel.getMorePets(PetRequest(type = args.petType, page = currentPage + 1))

        resultsViewModel.morePetsLiveData.observe(viewLifecycleOwner, Observer { morePets ->
            setCurrentPage(morePets.paginationInfo.currentPage, morePets.paginationInfo.totalPages)

            setLoading(false)

            petsList.addAll(morePets.pets)

            adapter.notifyItemRangeInserted(adapter.itemCount-1, morePets.pets.count())
        })
    }

    private fun showEmptyResults() {
        // TODO: Add empty results image
    }

    private fun setLoading(petsLoading: Boolean) {
        isLoading = petsLoading

        // TODO: Add loading animation
    }

    private fun setCurrentPage(pageNum: Int, lastPage: Int) {
        currentPage = pageNum

        isLastPage = currentPage == lastPage
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val FIRST_PAGE = 1
    }
}
