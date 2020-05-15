package com.example.mvvmpetfinder.pet_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.mvvmpetfinder.R
import com.example.mvvmpetfinder.data.model.Photo
import com.squareup.picasso.Picasso

class PetImageViewPagerAdapter(private val images: List<Photo>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view: View = LayoutInflater.from(container.context)
            .inflate(R.layout.pet_image_layout, container,false)

        val imageView: ImageView = view.findViewById(R.id.pet_details_image)

        loadImageAtPosition(position, imageView)

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` == view
    }

    override fun getCount(): Int {
        return images.count()
    }

    private fun loadImageAtPosition(position: Int, imageView: ImageView) {
        Picasso.get().load(images[position].medium)
            .fit()
            .placeholder(R.drawable.pet_placeholder)
            .error(R.drawable.pet_placeholder)
            .into(imageView)
    }
}