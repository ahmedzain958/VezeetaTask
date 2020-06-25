package com.vezeeta.vezeetatask.presentation.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vezeeta.vezeetatask.domain.model.ErrorModel
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.usecase.GetOffersUseCase
import com.vezeeta.vezeetatask.domain.usecase.base.UseCaseResponse
import com.vezeeta.vezeetatask.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class OffersViewModel constructor(private val getOffersUseCase: GetOffersUseCase) :
    BaseViewModel() {
    val offers: LiveData<List<Offer>>
        get() = _offers
    private val _offers by lazy { MutableLiveData<List<Offer>>() }

    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar
    private val _showProgressbar by lazy { MutableLiveData<Boolean>() }

    val messageData: LiveData<String>
        get() = _messageData
    private val _messageData by lazy { MutableLiveData<String>() }

    @ExperimentalCoroutinesApi
    fun getOffers() {
        _showProgressbar.value = true
        getOffersUseCase.invoke(null, object : UseCaseResponse<List<Offer>> {
            override fun onSuccess(result: List<Offer>) {
                _offers.value = result
                if (result.isNotEmpty())
                    _showProgressbar.value = false
            }

            override fun onError(errorModel: ErrorModel?) {
                _messageData.value = errorModel?.message
                _showProgressbar.value = false
            }
        })
    }
}