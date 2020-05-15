package com.example.mvvmpetfinder.results

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.data.model.Pet
import com.squareup.picasso.Picasso

class ResultsAdapter(
    private val context: Context,
    private val dataSet: List<Pet>,
    private val navController: NavController
) :
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
        Picasso.get().load(dataSet[position].photos?.firstOrNull()?.small)
            .fit()
            .placeholder(R.drawable.pet_placeholder)
            .error(R.drawable.pet_placeholder)
            .into(holder.image)

        holder.name.text = context.getString(R.string.pet_name_with_label, dataSet[position].name)
        holder.age.text = context.getString(R.string.pet_age_with_label, dataSet[position].age)
        // TODO: Build this separately to include other breeds/mix
        holder.breed.text = context.getString(R.string.pet_breed_with_label, dataSet[position].breeds.primary)
        holder.gender.text = context.getString(R.string.pet_gender_with_label, dataSet[position].gender)
        dataSet[position].goodWith?.let {
            holder.goodWithDogs.visibility =  if (it.dogs) { VISIBLE } else { GONE }
            holder.goodWithCats.visibility =  if (it.cats) { VISIBLE } else { GONE }
            holder.goodWithChildren.visibility =  if (it.children) { VISIBLE } else { GONE }
        }

        holder.itemView.setOnClickListener {
            val action = ResultsFragmentDirections.actionResultsFragmentToPetDetailsFragment(dataSet[position])
            navController.navigate(action)
        }
    }

    override fun getItemId(position: Int): Long {
        return dataSet[position].id.toLong()
    }

    class PetViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.pet_image)
        val name: TextView = view.findViewById(R.id.pet_name)
        val age: TextView = view.findViewById(R.id.pet_age)
        val breed: TextView = view.findViewById(R.id.pet_breed)
        val gender: TextView = view.findViewById(R.id.pet_gender)
        val goodWithDogs: ImageView = view.findViewById(R.id.good_with_dogs)
        val goodWithCats: ImageView = view.findViewById(R.id.good_with_cats)
        val goodWithChildren: ImageView = view.findViewById(R.id.good_with_children)
    }
}