package com.vezeeta.vezeetatask.di

import com.vezeeta.vezeetatask.data.repository.OffersRepositoryImp
import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.repository.OffersRepository
import com.vezeeta.vezeetatask.domain.usecase.offers.GetOffersUseCase
import com.vezeeta.vezeetatask.presentation.offers.OffersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
val offersModule = module {
    factory<OffersRepository> { OffersRepositoryImp(get()) }
    factory { createGetOffersUseCase(get(), get()) }
    viewModel { OffersViewModel(get(), get()) }
}


fun createGetOffersUseCase(
    offersRepository: OffersRepository,
    apiErrorHandle: ApiErrorHandle
): GetOffersUseCase {
    return GetOffersUseCase(
        offersRepository,
        apiErrorHandle
    )
}