package com.vezeeta.vezeetatask.presentation.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.databinding.OffersListFragmentBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class OffersListFragment : Fragment() {
    val viewModel by viewModel<OffersViewModel>()
    private lateinit var binding: OffersListFragmentBinding
    private var mAdapter: OffersAdapter? = null

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.offers_list_fragment, container, false)
        mAdapter = OffersAdapter()
        binding.recyclerviewOffers.adapter = mAdapter
        viewModel.getOffers()
        viewModel.offers.observe(viewLifecycleOwner, Observer { offerList ->
            offerList?.let {
                mAdapter?.mOffersList = it
            }
        })
        viewModel.showProgressbar.observe(viewLifecycleOwner, Observer { isVisible ->
            binding.progressbar.visibility = if (isVisible) View.VISIBLE else View.GONE
        })
        viewModel.messageData.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        })
        return binding.root
    }

}