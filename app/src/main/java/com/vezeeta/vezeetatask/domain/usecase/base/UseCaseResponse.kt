package com.vezeeta.vezeetatask.domain.usecase.base

import com.vezeeta.vezeetatask.domain.model.ErrorModel


interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}

