package com.vezeeta.vezeetatask.presentation.offers

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.vezeeta.vezeetatask.R

/**
 * Created by Ahmed Zain on 6/25/2020.
 */
object ListOffersBindings {
    @JvmStatic
    @BindingAdapter("bind:mainImage")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Picasso.get().load(imageUrl).error(R.drawable.ic_loading).into(view)
    }
}