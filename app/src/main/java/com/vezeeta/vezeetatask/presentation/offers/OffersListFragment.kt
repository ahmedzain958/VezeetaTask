package com.vezeeta.vezeetatask.presentation.offers

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
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
        setHasOptionsMenu(true)
        binding = getViewDataBinding()
        binding.progressbar.show()
        offersAdapter = OffersAdapter()
        binding.recyclerviewOffers.adapter = offersAdapter
        viewModel.getOffers()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getOffers()
        }
        viewModel.offers.observe(viewLifecycleOwner, Observer { offerList ->
            offerList.let {
                offersAdapter.mOffersList = it
                binding.swipeRefreshLayout.isRefreshing = false
                Log.d(TAG, "offers count= ${it.size}")
            }
        })
        viewModel.showProgressbar.observe(viewLifecycleOwner, Observer { isVisible ->
            if (isVisible) binding.progressbar.show() else binding.progressbar.hide()
        })
        viewModel.messageData.observe(viewLifecycleOwner, Observer { message ->
            Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        })
        viewModel.signedOut.observe(viewLifecycleOwner, Observer { signedOut ->
            if (signedOut) {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_offersListFragment_to_loginFragment)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    @ExperimentalCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_logout -> {
                viewModel.signOut()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}