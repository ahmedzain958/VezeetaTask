package com.vezeeta.vezeetatask.presentation.offers.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vezeeta.vezeetatask.domain.model.ErrorModel
import com.vezeeta.vezeetatask.domain.model.OfferDetail
import com.vezeeta.vezeetatask.domain.usecase.base.UseCaseResponse
import com.vezeeta.vezeetatask.domain.usecase.offers.details.GetOfferDetailsUseCase
import com.vezeeta.vezeetatask.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class OfferDetailsViewModel(
    private val getOfferDetailsUseCase: GetOfferDetailsUseCase

) : BaseViewModel() {
    val offerDetails: LiveData<OfferDetail>
        get() = _offerDetails
    private val _offerDetails by lazy { MutableLiveData<OfferDetail>() }

    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar
    private val _showProgressbar by lazy { MutableLiveData<Boolean>() }

    val messageData: LiveData<String>
        get() = _messageData
    private val _messageData by lazy { MutableLiveData<String>() }


    @ExperimentalCoroutinesApi
    fun getOfferDetails(offerKey: String) {
        _showProgressbar.value = true
        getOfferDetailsUseCase.invoke(
            GetOfferDetailsUseCase.Params(offerKey = offerKey),
            object : UseCaseResponse<OfferDetail> {
                override fun onSuccess(result: OfferDetail) {
                    _offerDetails.value = result
                    _showProgressbar.value = false
                }

                override fun onError(errorModel: ErrorModel?) {
                    _messageData.value = errorModel?.message
                    _showProgressbar.value = false
                }
            })
    }

}