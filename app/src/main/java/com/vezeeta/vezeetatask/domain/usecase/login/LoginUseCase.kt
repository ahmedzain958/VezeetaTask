package com.vezeeta.vezeetatask.domain.usecase.login

import com.vezeeta.vezeetatask.data.source.remote.exception.ApiErrorHandle
import com.vezeeta.vezeetatask.domain.model.User
import com.vezeeta.vezeetatask.domain.repository.LoginRepository
import com.vezeeta.vezeetatask.domain.usecase.base.UseCase

class LoginUseCase constructor(
    private val loginRepository: LoginRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<User, LoginUseCase.Params?>(apiErrorHandle) {
    override suspend fun run(params: Params?): User {
        return loginRepository.login(params?.email ?: "", params?.password ?: "")
    }

    data class Params(val email: String, val password: String)
}