package com.vezeeta.vezeetatask.presentation.offers.details

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vezeeta.vezeetatask.R

class OfferImagesPageAdapter(
    var imagesUrls: List<String>,
    var offerListener: OfferListener
) : PagerAdapter() {
    interface OfferListener {
        fun onImageLoadedSuccessfully()
        fun onImageErrorLoading()
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return imagesUrls.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.contentDescription = container.context.getString(R.string.no_image)
        Picasso.get()
            .load(imagesUrls[position])
            .placeholder(R.drawable.progress_animation)
            .fit()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    offerListener.onImageLoadedSuccessfully()
                }

                override fun onError(e: Exception?) {
                    imageView.setImageBitmap(null)
                    offerListener.onImageErrorLoading()
                }
            })
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}