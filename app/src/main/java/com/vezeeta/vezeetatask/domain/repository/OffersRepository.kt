package com.vezeeta.vezeetatask.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.model.OfferDetail

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface OffersRepository {
      var offersLiveData: LiveData<PagedList<Offer>>
    suspend fun getOffers(page: Int): PagedList<Offer>
    suspend fun getOfferDetails(offerKey: String): OfferDetail
    suspend fun searchOffers(searchText: String): List<Offer>
}