package com.vezeeta.vezeetatask.data.source.remote

import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.model.OfferDetail
import com.vezeeta.vezeetatask.domain.model.User

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class RemoteDataSourceImp(
    private val vezeetaApi: VezeetaApi
) : RemoteDataSource {
    override suspend fun login(
        email: String,
        password: String
    ): User {
        return vezeetaApi.login(email, password)
    }

    override suspend fun getOffers(page: Int): List<Offer> {
        return vezeetaApi.getOffers(page).offersList
    }

    override suspend fun getOfferDetails(offerKey: String): OfferDetail {
        return vezeetaApi.getOfferDetails(offerKey)
    }

    override suspend fun searchOffers(searchText: String): List<Offer> {
        return vezeetaApi.searchOffers(searchText).offersList
    }
}