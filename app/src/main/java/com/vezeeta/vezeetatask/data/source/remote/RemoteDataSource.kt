package com.vezeeta.vezeetatask.data.source.remote

import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.model.OfferDetail

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface RemoteDataSource {
    suspend fun getOffers(page: Int): List<Offer>
    suspend fun getOfferDetails(offerKey: String): OfferDetail
    suspend fun searchOffers(searchText: String): List<Offer>
}