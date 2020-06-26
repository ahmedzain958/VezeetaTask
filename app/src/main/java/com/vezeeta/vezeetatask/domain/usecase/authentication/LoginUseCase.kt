package com.vezeeta.vezeetatask.domain.usecase.authentication

import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.model.User
import com.vezeeta.vezeetatask.domain.repository.AuthenticationRepository
import com.vezeeta.vezeetatask.domain.usecase.base.UseCase

class LoginUseCase constructor(
    private val authenticationRepository: AuthenticationRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<User, LoginUseCase.Params?>(apiErrorHandle) {
    override suspend fun run(params: Params?): User {
        return authenticationRepository.login(params?.email ?: "", params?.password ?: "")
    }

    data class Params(val email: String, val password: String)
}