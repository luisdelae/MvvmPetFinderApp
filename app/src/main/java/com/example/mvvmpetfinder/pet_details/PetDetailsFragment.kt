package com.example.mvvmpetfinder.pet_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.data.model.Pet

// TODO: Build the actual fragment view
// TODO: Seem to have all data needed. Do I though? If so, no repository needed. Delete it.
class PetDetailsFragment : Fragment() {

    private val args: PetDetailsFragmentArgs by navArgs()

    private lateinit var viewModel: PetDetailsViewModel

    private lateinit var pet: Pet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        pet = args.PetDetails

        return inflater.inflate(R.layout.pet_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val petNameGreeting = view.findViewById<TextView>(R.id.pet_name_greeting)
        petNameGreeting.text = requireContext().getString(R.string.pet_name_greeting, pet.name)

        val petImageViewPager = view.findViewById<ViewPager>(R.id.pet_images_pager)
        pet.photos?.let { photos ->
            petImageViewPager.adapter = PetImageViewPagerAdapter(photos)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}
