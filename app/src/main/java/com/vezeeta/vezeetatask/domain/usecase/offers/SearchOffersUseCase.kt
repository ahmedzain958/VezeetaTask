package com.vezeeta.vezeetatask.domain.usecase.offers

import androidx.paging.PagedList
import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.repository.OffersRepository
import com.vezeeta.vezeetatask.domain.usecase.base.UseCase

class SearchOffersUseCase constructor(
    private val offersRepository: OffersRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<List<Offer>, SearchOffersUseCase.Params?>(apiErrorHandle) {
    override suspend fun run(params: Params?): List<Offer> {
        return offersRepository.searchOffers(params?.searchText ?: "")
    }

    data class Params(val searchText: String)
}