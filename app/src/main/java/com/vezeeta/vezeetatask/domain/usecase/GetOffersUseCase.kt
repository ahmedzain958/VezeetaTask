package com.vezeeta.vezeetatask.domain.usecase

import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.repository.OffersRepository
import com.vezeeta.vezeetatask.domain.usecase.base.UseCase

class GetOffersUseCase constructor(
    private val offersRepository: OffersRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<List<Offer>, GetOffersUseCase.Params?>(apiErrorHandle) {
    override suspend fun run(params: Params?): List<Offer> {
        return offersRepository.getOffers(params?.page ?: 1)
    }

    data class Params(val page: Int = 1)
}