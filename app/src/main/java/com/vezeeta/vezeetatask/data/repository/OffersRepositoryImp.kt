package com.vezeeta.vezeetatask.data.repository

import com.vezeeta.vezeetatask.data.source.local.LocalDataSource
import com.vezeeta.vezeetatask.data.source.remote.RemoteDataSource
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.model.OfferDetail
import com.vezeeta.vezeetatask.domain.repository.OffersRepository

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class OffersRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : OffersRepository {
    override suspend fun getOffers(page: Int): List<Offer> {
        return remoteDataSource.getOffers(page)
    }

    override suspend fun getOfferDetails(offerKey: String): OfferDetail {
        return remoteDataSource.getOfferDetails(offerKey)
    }

    override suspend fun searchOffers(searchText: String): List<Offer> {
        return remoteDataSource.searchOffers(searchText)
    }

}