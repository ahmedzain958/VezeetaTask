package com.vezeeta.vezeetatask.di

import com.vezeeta.vezeetatask.data.repository.AuthenticationRepositoryImp
import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.repository.AuthenticationRepository
import com.vezeeta.vezeetatask.domain.usecase.authentication.CheckIsLoggedUserUseCase
import com.vezeeta.vezeetatask.domain.usecase.authentication.LoginUseCase
import com.vezeeta.vezeetatask.domain.usecase.authentication.SignOutUseCase
import com.vezeeta.vezeetatask.presentation.login.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ahmed Zain on 6/24/2020.
 */
@ExperimentalCoroutinesApi
val authenticationModule = module {
    factory<AuthenticationRepository> { AuthenticationRepositoryImp(get(), get()) }
    factory { createLoginUseCase(get(), get()) }
    factory { createIsLoggedUserUseCase(get(), get()) }
    factory { createSignOutUseCase(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
}

fun createLoginUseCase(
    authenticationRepository: AuthenticationRepository,
    apiErrorHandle: ApiErrorHandle
): LoginUseCase {
    return LoginUseCase(authenticationRepository, apiErrorHandle)
}

fun createIsLoggedUserUseCase(
    authenticationRepository: AuthenticationRepository,
    apiErrorHandle: ApiErrorHandle
): CheckIsLoggedUserUseCase {
    return CheckIsLoggedUserUseCase(authenticationRepository, apiErrorHandle)
}

//created usecase here because it logic belongs to authentication not offers
fun createSignOutUseCase(
    authenticationRepository: AuthenticationRepository,
    apiErrorHandle: ApiErrorHandle
): SignOutUseCase {
    return SignOutUseCase(
        authenticationRepository,
        apiErrorHandle
    )
}