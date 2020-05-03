package com.example.mvvmpetfinder.results

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.data.model.Pet
import com.squareup.picasso.Picasso
import timber.log.Timber

class ResultsAdapter(private val dataSet: List<Pet>) :
    RecyclerView.Adapter<ResultsAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.results_row, parent, false)

        return PetViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {

        // Placeholder/Error image by Elisabeth Leunert
        // https://pixabay.com/users/Leunert-2332372/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=1517090
        Picasso.get().load( dataSet[position].photos.firstOrNull()?.small)
            .fit()
            .placeholder(R.drawable.pet_placeholder)
            .error(R.drawable.pet_placeholder)
            .into(holder.image)

        holder.name.text = "Name: ${ dataSet[position].name }"
        holder.age.text = "Age: ${ dataSet[position].age }"
        holder.breed.text = "Breed: ${ dataSet[position].breeds.primary }" // Build this separately to include other breeds/mix
        holder.gender.text = "Gender: ${ dataSet[position].gender }"
        holder.goodWithDogs.visibility =  if (dataSet[position].goodWith.dogs) { VISIBLE } else { GONE }
        holder.goodWithCats.visibility =  if (dataSet[position].goodWith.cats) { VISIBLE } else { GONE }
        holder.goodWithChildren.visibility =  if (dataSet[position].goodWith.children) { VISIBLE } else { GONE }
    }

    override fun getItemId(position: Int): Long {
        return dataSet[position].id.toLong()
    }

    class PetViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val image = view.findViewById<ImageView>(R.id.pet_image)
        val name = view.findViewById<TextView>(R.id.pet_name)
        val age = view.findViewById<TextView>(R.id.pet_age)
        val breed = view.findViewById<TextView>(R.id.pet_breed)
        val gender = view.findViewById<TextView>(R.id.pet_gender)
        val goodWithDogs = view.findViewById<ImageView>(R.id.good_with_dogs)
        val goodWithCats = view.findViewById<ImageView>(R.id.good_with_cats)
        val goodWithChildren = view.findViewById<ImageView>(R.id.good_with_children)

        init {
            view.setOnClickListener {
                Timber.d("Clicked adapter position: $adapterPosition")
                Timber.d("Id at adapter position: $itemId")
            }
        }
    }
}