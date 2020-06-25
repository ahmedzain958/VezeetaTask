package com.vezeeta.vezeetatask.presentation.offers

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.databinding.OffersListFragmentBinding
import com.vezeeta.vezeetatask.presentation.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class OffersListFragment : BaseFragment<OffersListFragmentBinding>() {
    private val viewModel by viewModel<OffersViewModel>()
    private lateinit var binding: OffersListFragmentBinding
    private lateinit var offersAdapter: OffersAdapter
    private val TAG = OffersListFragment::class.java.simpleName

    override fun getLayoutId(): Int {
        return R.layout.offers_list_fragment
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        binding.progressbar.show()
        offersAdapter = OffersAdapter()
        binding.recyclerviewOffers.adapter = offersAdapter
        viewModel.getOffers()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getOffers()
        }
        viewModel.offers.observe(viewLifecycleOwner, Observer { offerList ->
            offerList?.let {
                offersAdapter.mOffersList = it
                binding.swipeRefreshLayout.isRefreshing = false
                Log.d(TAG, "offers count= ${it.size}")
            }
        })
        viewModel.showProgressbar.observe(viewLifecycleOwner, Observer { isVisible ->
            if (isVisible) binding.progressbar.show() else binding.progressbar.hide()
        })
        viewModel.messageData.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        })
    }
}