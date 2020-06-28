package com.vezeeta.vezeetatask.presentation.offers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.databinding.HolderOfferBinding
import com.vezeeta.vezeetatask.domain.model.Offer
import kotlin.properties.Delegates

class OffersListAdapter(
    var onOfferClickListener: OnOfferClickListener
) :
    PagedListAdapter<Offer, OffersListAdapter.OfferViewHolder>(DiffUtilCallBack()) {

    interface OnOfferClickListener {
        fun onOfferClicked(offer: Offer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val holderOfferBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_offer, parent, false
        )
        return OfferViewHolder(holderOfferBinding)
    }

    inner class OfferViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun onBind(offer: Offer) {
            val holderOfferBinding = viewDataBinding as HolderOfferBinding
            holderOfferBinding.buttonDetails.setOnClickListener {
                onOfferClickListener.onOfferClicked(offer)
            }
            holderOfferBinding.offer = offer
        }
    }


    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
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