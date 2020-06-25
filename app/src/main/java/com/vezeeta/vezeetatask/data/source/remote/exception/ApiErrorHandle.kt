package com.vezeeta.vezeetatask.data.source.remote.exception

import com.vezeeta.vezeetatask.data.repository.ResourcesRepository
import com.vezeeta.vezeetatask.domain.model.ErrorModel
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * This class trace exceptions(api call or parse data or connection errors) &
 * depending on what exception returns [ErrorModel]
 *
 * */
class ApiErrorHandle(
    private val resourcesRepository: ResourcesRepository
) {

    fun traceErrorException(throwable: Throwable?): ErrorModel {
        val errorModel: ErrorModel? = when (throwable) {

            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                if (throwable.code() == 401) {
                    ErrorModel(
                        throwable.message(),
                        throwable.code(),
                        ErrorModel.ErrorStatus.UNAUTHORIZED
                    )
                } else {
                    getHttpError(throwable.response()?.errorBody())
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                ErrorModel(resourcesRepository.getSocketTimeoutExceptionMessage(), ErrorModel.ErrorStatus.TIMEOUT)
            }

            // handle connection error
            is IOException ->
            {
                ErrorModel(
                    resourcesRepository.getNetworkExceptionMessage(),
                    ErrorModel.ErrorStatus.NO_CONNECTION
                )
            }
            is NoInternetConnectionException -> {
                ErrorModel(
                    resourcesRepository.getNetworkExceptionMessage(),
                    ErrorModel.ErrorStatus.NO_CONNECTION
                )
            }
            else -> null
        }
        return errorModel ?: ErrorModel("No Defined Error!", 0, ErrorModel.ErrorStatus.BAD_RESPONSE)
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            // use response body to get error detail
            ErrorModel(body.toString(), 400, ErrorModel.ErrorStatus.BAD_RESPONSE)
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(message = e.message, errorStatus = ErrorModel.ErrorStatus.NOT_DEFINED)
        }

    }
}