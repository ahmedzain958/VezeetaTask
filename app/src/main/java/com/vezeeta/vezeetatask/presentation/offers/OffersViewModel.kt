package com.vezeeta.vezeetatask.presentation.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vezeeta.vezeetatask.domain.model.ErrorModel
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.usecase.authentication.SignOutUseCase
import com.vezeeta.vezeetatask.domain.usecase.base.UseCaseResponse
import com.vezeeta.vezeetatask.domain.usecase.offers.GetOffersUseCase
import com.vezeeta.vezeetatask.domain.usecase.offers.SearchOffersUseCase
import com.vezeeta.vezeetatask.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class OffersViewModel constructor(
    private val getOffersUseCase: GetOffersUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val searchOffersUseCase: SearchOffersUseCase
) :
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

    val signedOut: LiveData<Boolean>
        get() = _signedOut
    private val _signedOut by lazy { MutableLiveData<Boolean>() }

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

    @ExperimentalCoroutinesApi
    fun signOut() {
        _showProgressbar.value = true
        signOutUseCase.invoke(null, object : UseCaseResponse<Boolean> {
            override fun onSuccess(result: Boolean) {
                _signedOut.value = result
                _showProgressbar.value = false
            }

            override fun onError(errorModel: ErrorModel?) {
                _showProgressbar.value = false
            }
        })
    }

    @ExperimentalCoroutinesApi
    fun search(searchText: String) {
        _showProgressbar.value = true
        searchOffersUseCase.invoke(
            SearchOffersUseCase.Params(searchText)
            , object : UseCaseResponse<List<Offer>> {
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