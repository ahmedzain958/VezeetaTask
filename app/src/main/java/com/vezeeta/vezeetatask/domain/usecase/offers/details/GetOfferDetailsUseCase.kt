package com.vezeeta.vezeetatask.domain.usecase.offers.details

import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.model.OfferDetail
import com.vezeeta.vezeetatask.domain.repository.OffersRepository
import com.vezeeta.vezeetatask.domain.usecase.base.UseCase

class GetOfferDetailsUseCase constructor(
    private val offersRepository: OffersRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<OfferDetail, GetOfferDetailsUseCase.Params?>(apiErrorHandle) {
    override suspend fun run(params: Params?): OfferDetail {
        return offersRepository.getOfferDetails(params?.offerKey ?: "")
    }

    data class Params(val offerKey: String)
}