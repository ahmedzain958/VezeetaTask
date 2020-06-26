package com.vezeeta.vezeetatask.di

import com.vezeeta.vezeetatask.data.repository.LoginRepositoryImp
import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.repository.LoginRepository
import com.vezeeta.vezeetatask.domain.usecase.login.LoginUseCase
import com.vezeeta.vezeetatask.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
val loginModule = module {
    factory<LoginRepository> { LoginRepositoryImp(get(), get()) }
    factory { createLoginUseCase(get(), get()) }
    viewModel { LoginViewModel(get()) }
}


fun createLoginUseCase(
    loginRepository: LoginRepository,
    apiErrorHandle: ApiErrorHandle
): LoginUseCase {
    return LoginUseCase(loginRepository, apiErrorHandle)
}