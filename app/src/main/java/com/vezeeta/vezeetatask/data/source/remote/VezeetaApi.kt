package com.vezeeta.vezeetatask.data.source.remote

import com.vezeeta.vezeetatask.domain.model.OfferDetail
import com.vezeeta.vezeetatask.domain.model.Response
import com.vezeeta.vezeetatask.domain.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
interface VezeetaApi {
    @POST("/dummyLogin")
    suspend fun login(
        @Query("email") email: String ,
        @Query("password") password: String
    ): User

    @GET("/dummyOffers")
    suspend fun getOffers(
        @Query("page") page: Int = 1
    ): Response

    @GET("/dummyOfferDetails")
    suspend fun getOfferDetails(
        @Query("offerKey") offerKey: String
    ): OfferDetail


    @GET("/dummyOffersSearch")
    suspend fun searchOffers(
        @Query("text") text: String
    ): Response
}