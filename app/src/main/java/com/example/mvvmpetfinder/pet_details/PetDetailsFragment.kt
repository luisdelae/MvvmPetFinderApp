package com.example.mvvmpetfinder.pet_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.data.model.Attributes
import com.example.mvvmpetfinder.data.model.Breeds
import com.example.mvvmpetfinder.data.model.GoodWith
import com.example.mvvmpetfinder.data.model.Pet

// TODO: Add videos to the adapter?
// TODO: Finish up the details
class PetDetailsFragment : Fragment() {

    private val args: PetDetailsFragmentArgs by navArgs()

    private lateinit var pet: Pet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        pet = args.PetDetails

        return inflater.inflate(R.layout.pet_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        loadDetails(view)

        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * Loads details of the animal
     */
    private fun loadDetails(view: View) {
        // Pet name
        val petNameGreeting = view.findViewById<TextView>(R.id.pet_name_greeting)
        petNameGreeting.text = requireContext().getString(R.string.pet_name_greeting, pet.name)

        // Images and videos
        val petImageViewPager = view.findViewById<ViewPager>(R.id.pet_images_pager)
        pet.photos?.let { photos ->
            petImageViewPager.adapter = PetImageViewPagerAdapter(photos)
        }

        // Description
        val petDescription = view.findViewById<TextView>(R.id.pet_description)
        petDescription.text = pet.description

        // Breed
        val breed = view.findViewById<TextView>(R.id.breed_text)
        breed.text = buildBreed(pet.breeds)

        // Age
        val age = view.findViewById<TextView>(R.id.age_text)
        age.text = pet.age

        // Size
        val size = view.findViewById<TextView>(R.id.size_text)
        size.text = pet.size

        // Adoption status
        val status = view.findViewById<TextView>(R.id.status_text)
        status.text = pet.status

        // Good with
        pet.goodWith?.let { goodWith ->
            buildGoodWith(view, goodWith)
        }

        // Attributes
        pet.attributes?.let { attrs ->
            buildAttributes(view, attrs)
        }
    }

    /**
     * Builds breed of the animal based on given breed values
     */
    private fun buildBreed(breed: Breeds): String {
        var breedString = ""

        if (breed.unknown) {
            breedString = "Unknown"
        }

        if (!breed.primary.isNullOrEmpty()) {
            breedString = breed.primary

            if (!breed.secondary.isNullOrEmpty()) {
                breedString += "/${breed.secondary}"
            }
        }

        return breedString
    }

    /**
     * Sets visibility of breed icons
     */
    private fun buildGoodWith(view: View, goodWith: GoodWith) {
        val goodWithDogs = view.findViewById<ImageView>(R.id.good_with_dogs)
        val goodWithCats = view.findViewById<ImageView>(R.id.good_with_cats)
        val goodWithChildren = view.findViewById<ImageView>(R.id.good_with_children)

        if (goodWith.dogs) goodWithDogs.visibility =
            View.VISIBLE else goodWithDogs.visibility = View.GONE
        if (goodWith.cats) goodWithCats.visibility =
            View.VISIBLE else goodWithCats.visibility = View.GONE
        if (goodWith.children) goodWithChildren.visibility =
            View.VISIBLE else goodWithChildren.visibility = View.GONE

    }

    /**
     * Checks or unchecks the different attribute checkboxes
     */
    private fun buildAttributes(view: View, attributes: Attributes) {
        val spayedNeutered = view.findViewById<CheckBox>(R.id.spay_neuter_check)
        val houseTrained = view.findViewById<CheckBox>(R.id.house_trained_check)
        val declawed = view.findViewById<CheckBox>(R.id.declawed_check)
        val specialNeeds = view.findViewById<CheckBox>(R.id.special_needs_check)
        val shotsCurrent = view.findViewById<CheckBox>(R.id.shots_current_check)

        spayedNeutered.isChecked = attributes.spayedNeutered
        houseTrained.isChecked = attributes.houseTrained
        declawed.isChecked = attributes.declawed
        specialNeeds.isChecked = attributes.specialNeeds
        shotsCurrent.isChecked = attributes.shortsCurrent
    }
}
