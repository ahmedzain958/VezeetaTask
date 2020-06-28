package com.vezeeta.vezeetatask.domain.usecase.offers

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.model.Offer
import com.vezeeta.vezeetatask.domain.repository.OffersRepository
import com.vezeeta.vezeetatask.domain.usecase.base.UseCase

class GetOffersUseCase constructor(
    private val offersRepository: OffersRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<LiveData<PagedList<Offer>>, Any?>(apiErrorHandle) {
    override suspend fun run(params: Any?): LiveData<PagedList<Offer>> {
        return offersRepository.offersLiveData
    }
}