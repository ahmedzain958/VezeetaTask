package com.vezeeta.vezeetatask.domain.usecase.authentication

import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.repository.AuthenticationRepository
import com.vezeeta.vezeetatask.domain.usecase.base.UseCase

class SignOutUseCase constructor(
    private val authenticationRepository: AuthenticationRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<Boolean, Any?>(apiErrorHandle) {
    override suspend fun run(params: Any?): Boolean {
        return authenticationRepository.logOut()
    }
}