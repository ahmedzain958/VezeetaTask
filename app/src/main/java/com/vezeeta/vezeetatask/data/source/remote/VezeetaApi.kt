package com.vezeeta.vezeetatask.data.source.remote

import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.model.OfferDetail
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface VezeetaApi {
    @GET("/dummyOffers")
    suspend fun getOffers(
        @Query("page") page: Int
    ): List<Offer>

    @GET("/dummyOfferDetails")
    suspend fun getOfferDetails(
        @Query("offerKey") offerKey: String
    ): OfferDetail


    @GET("/dummyOffersSearch")
    suspend fun searchOffers(
        @Query("text") text: String
    ): List<Offer>
}