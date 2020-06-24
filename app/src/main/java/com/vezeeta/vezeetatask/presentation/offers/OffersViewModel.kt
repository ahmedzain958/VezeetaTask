package com.vezeeta.vezeetatask.presentation.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.post.domain.usecase.GetOffersUseCase
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.presentation.base.BaseViewModel

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class OffersViewModel constructor(private val getOffersUseCase: GetOffersUseCase) :
    BaseViewModel() {
    val offers: LiveData<List<Offer>>
        get() = _offers
    private val _offers = MutableLiveData<List<Offer>>()
}