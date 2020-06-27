package com.vezeeta.vezeetatask.presentation.offers

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.presentation.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class OffersListFragment :
    BaseFragment<OffersListFragmentBinding>(),
    OffersListAdapter.OnOfferClickListener {
    private val viewModel by viewModel<OffersViewModel>()
    private lateinit var binding: OffersListFragmentBinding
    private lateinit var offersListAdapter: OffersListAdapter
    private val TAG = OffersListFragment::class.java.simpleName

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    @ExperimentalCoroutinesApi
    private val textChangedWatcher = object : TextWatcher {
        private var searchFor = ""
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()
            if (searchText == searchFor)
                return
            searchFor = searchText
            launch {
                delay(300)  //debounce timeOut
                if (searchText != searchFor)
                    return@launch
                if (searchText.isNotEmpty())
                    viewModel.search(searchText)
                else
                    viewModel.getOffers()
            }
        }

        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    }

    override fun getLayoutId(): Int {
        return R.layout.offers_list_fragment
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = getViewDataBinding()
        viewModel.getOffers()
        setupUI()
        setupObservers(view)
    }

    @ExperimentalCoroutinesApi
    private fun setupUI() {
        binding.apply {
            progressbar.show()
            offersListAdapter = OffersListAdapter(this@OffersListFragment)
            recyclerviewOffers.adapter = offersListAdapter
            swipeRefreshLayout.setOnRefreshListener {
                if (edittextSearch.text.toString().trim().isEmpty())
                    viewModel.getOffers()
                else
                    viewModel.search(edittextSearch.text.toString())
            }
            edittextSearch.addTextChangedListener(textChangedWatcher)
        }
    }

    private fun setupObservers(view: View) {
        viewModel.apply {
            offers.observe(viewLifecycleOwner, Observer { offerList ->
                offerList?.let {
                    offersListAdapter.mOffersList = it
                    binding.swipeRefreshLayout.isRefreshing = false
                    Log.d(TAG, "offers count= ${it.size}")
                }
            })
            showProgressbar.observe(viewLifecycleOwner, Observer { isVisible ->
                if (isVisible) binding.progressbar.show() else binding.progressbar.hide()
            })
            messageData.observe(viewLifecycleOwner, Observer { message ->
                //no internet swipe refresh is displayed; this line hides it
                binding.swipeRefreshLayout.isRefreshing = false
                Snackbar.make(
                    view,
                    message,
                    Snackbar.LENGTH_LONG
                ).show()
            })
            signedOut.observe(viewLifecycleOwner, Observer { signedOut ->
                if (signedOut) {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_offersListFragment_to_loginFragment)
                }
            })
        }
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

    override fun onOfferClicked(offer: Offer) {
        val action =
            OffersListFragmentDirections.actionOffersListFragmentToOfferDetailsFragment(offer)
        Navigation.findNavController(binding.root).navigate(action)
    }


}