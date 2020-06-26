package com.vezeeta.vezeetatask.presentation.offers.details

import android.os.Bundle
import android.util.Log
import android.view.View
import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.databinding.OfferDetailsFragmentBinding
import com.vezeeta.vezeetatask.presentation.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class OfferDetailsFragment : BaseFragment<OfferDetailsFragmentBinding>() {
    private val TAG = OfferDetailsFragment::class.java.simpleName
    private val viewModel by viewModel<OfferDetailsViewModel>()
    private lateinit var binding: OfferDetailsFragmentBinding
    override fun getLayoutId(): Int {
        return R.layout.offer_details_fragment
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        arguments?.let {
            val args = OfferDetailsFragmentArgs.fromBundle(it)
            Log.d(TAG, args.offer.toString())
        }

    }

}