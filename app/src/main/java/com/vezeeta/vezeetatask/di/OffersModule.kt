package com.vezeeta.vezeetatask.di

import com.vezeeta.vezeetatask.data.repository.OffersRepositoryImp
import com.vezeeta.vezeetatask.data.source.remote.VezeetaApi
import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.repository.OffersRepository
import com.vezeeta.vezeetatask.domain.usecase.GetOffersUseCase
import com.vezeeta.vezeetatask.presentation.offers.OffersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
val offersModule = module {
    single { createOffersService(get()) }
    factory<OffersRepository> { OffersRepositoryImp(get(), get()) }
    factory { ApiErrorHandle(get()) }
    factory { createGetOffersUseCase(get(), get()) }
    viewModel { OffersViewModel(get()) }
}

fun createOffersService(retrofit: Retrofit): VezeetaApi {
    return retrofit.create(VezeetaApi::class.java)
}

fun createGetOffersUseCase(
    offersRepository: OffersRepository,
    apiErrorHandle: ApiErrorHandle
): GetOffersUseCase {
    return GetOffersUseCase(offersRepository, apiErrorHandle)
}