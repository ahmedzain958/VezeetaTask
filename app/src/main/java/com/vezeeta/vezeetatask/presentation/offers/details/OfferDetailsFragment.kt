package com.vezeeta.vezeetatask.presentation.offers.details

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.databinding.OfferDetailsFragmentBinding
import com.vezeeta.vezeetatask.domain.model.OfferDetail
import com.vezeeta.vezeetatask.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.offer_details_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class OfferDetailsFragment : BaseFragment<OfferDetailsFragmentBinding>(),
    OfferImagesPageAdapter.OfferListener {
    private val TAG = OfferDetailsFragment::class.java.simpleName
    private val viewModel by viewModel<OfferDetailsViewModel>()
    private lateinit var binding: OfferDetailsFragmentBinding
    private lateinit var offerImagesPageAdapter: OfferImagesPageAdapter
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun getLayoutId(): Int {
        return R.layout.offer_details_fragment
    }

    private var currentPosition = 0
    private var offerImagesSize = 0

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        binding.progressbar.show()
        binding.viewModel = viewModel
        arguments?.let {
            val args = OfferDetailsFragmentArgs.fromBundle(it)
            val offer = args.offer
            binding.textviewOfferName.text = offer.offerName
            args.offer.offerKey?.let { offerKey ->
                viewModel.apply {
                    getOfferDetails(offerKey)
                    setupObservers(view)
                }
            }
        }
        setupUI()
        //start Repeating Task
        mHandlerTask.run()
    }

    private fun OfferDetailsViewModel.setupObservers(
        view: View
    ) {
        showProgressbar.observe(viewLifecycleOwner, Observer { isVisible ->
            if (isVisible) binding.progressbar.show() else binding.progressbar.hide()
        })
        messageData.observe(viewLifecycleOwner, Observer { message ->
            Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        })
        offerDetails.observe(viewLifecycleOwner, Observer { offerDetail: OfferDetail ->
            binding.textviewOfferDesc.text = offerDetail.desc
            offerImagesPageAdapter = OfferImagesPageAdapter(
                offerDetail.images,
                this@OfferDetailsFragment
            )
            binding.viewpager.adapter = offerImagesPageAdapter
            offerImagesSize = offerDetail.images.size
            binding.tvImageIndex.text = "1/$offerImagesSize"
        })
    }

    private fun setupUI() {
        binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                binding.tvImageIndex.text = (position + 1).toString() + "/" + offerImagesSize
            }

        })
        binding.imageviewPrevious.setOnClickListener {
            if (currentPosition > 0) {
                binding.viewpager.currentItem = currentPosition - 1
            } else {
                binding.viewpager.currentItem = offerImagesSize - 1
            }
        }
        binding.imageviewNext.setOnClickListener {
            if (currentPosition < offerImagesSize - 1) {
                binding.viewpager.currentItem = currentPosition + 1
            } else {
                binding.viewpager.currentItem = 0
            }
        }
    }

    private var mHandler = Handler()
    private var mHandlerTask: Runnable = object : Runnable {
        override fun run() {
            binding.imageviewNext.performClick()
            //slides every 7 secs
            mHandler.postDelayed(this, 7000L)
        }
    }

    override fun onImageLoadedSuccessfully() {
        binding.apply {
            layoutImageNotFound.visibility = View.GONE
            imageview_hand?.run {
                visibility = View.VISIBLE
                alpha = 0.0f
                animate().alpha(1.0f).setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        visibility = View.GONE
                    }
                }).duration = 1200
            }
        }
    }

    override fun onImageErrorLoading() {
        binding.layoutImageNotFound.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        //stop Repeating Task
        mHandler.removeCallbacks(mHandlerTask)
    }

}