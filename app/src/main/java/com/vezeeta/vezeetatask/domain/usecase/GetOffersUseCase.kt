package com.android.post.domain.usecase

import com.android.post.domain.usecase.base.UseCase
import com.vezeeta.vezeetatask.domain.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.repository.OffersRepository

class GetOffersUseCase constructor(
    private val offersRepository: OffersRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<List<Offer>, GetOffersUseCase.Params?>(apiErrorHandle) {
    override suspend fun run(params: Params?): List<Offer> {
        return offersRepository.getOffers(params?.page ?: 1)
    }

    data class Params(val page: Int)
}