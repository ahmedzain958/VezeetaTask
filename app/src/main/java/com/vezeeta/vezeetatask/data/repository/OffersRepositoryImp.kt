package com.vezeeta.vezeetatask.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.vezeeta.vezeetatask.data.source.paging.OffersDataSource
import com.vezeeta.vezeetatask.data.source.remote.RemoteDataSource
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.model.OfferDetail
import com.vezeeta.vezeetatask.domain.repository.OffersRepository

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
class OffersRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val offersDataSource: OffersDataSource
) : OffersRepository {
    override lateinit var offersLiveData: LiveData<PagedList<Offer>>
    override suspend fun getOffers(page: Int): PagedList<Offer> {
        TODO("Not yet implemented")
    }

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        offersLiveData = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Offer> {

        val dataSourceFactory = object : DataSource.Factory<Int, Offer>() {
            override fun create(): DataSource<Int, Offer> {
                return offersDataSource
            }
        }
        return LivePagedListBuilder<Int, Offer>(dataSourceFactory, config)
    }

    /*override suspend fun getOffers(page: Int): retrofit2.Response<Response> {
        return remoteDataSource.getOffers(page)
    }*/

    override suspend fun getOfferDetails(offerKey: String): OfferDetail {
        return remoteDataSource.getOfferDetails(offerKey)
    }

    override suspend fun searchOffers(searchText: String): List<Offer> {
        return remoteDataSource.searchOffers(searchText)
    }
}