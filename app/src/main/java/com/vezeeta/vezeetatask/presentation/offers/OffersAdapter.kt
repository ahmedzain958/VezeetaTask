package com.vezeeta.vezeetatask.presentation.offers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.databinding.HolderOfferBinding
import com.vezeeta.vezeetatask.domain.model.Offer
import kotlin.properties.Delegates

class OffersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mOffersList: List<Offer> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderOfferBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_offer, parent, false
        )
        return OfferViewHolder(holderOfferBinding)
    }

    override fun getItemCount(): Int {
        return if (mOffersList.isNullOrEmpty()) 0 else mOffersList.size
    }

    private fun getItem(position: Int): Offer {
        return mOffersList[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as OfferViewHolder).onBind(getItem(position))
    }

    inner class OfferViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun onBind(offer: Offer) {
            val holderOfferBinding = viewDataBinding as HolderOfferBinding
            holderOfferBinding.offer = offer
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.offerKey == newItem.offerKey
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.offerKey == newItem.offerKey
                    && oldItem.mainImage == newItem.mainImage
                    && oldItem.offerName == newItem.offerName
        }

    }

}