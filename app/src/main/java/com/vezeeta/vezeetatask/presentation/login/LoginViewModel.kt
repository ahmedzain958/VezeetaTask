package com.vezeeta.vezeetatask.presentation.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vezeeta.vezeetatask.domain.model.ErrorModel
import com.vezeeta.vezeetatask.domain.model.User
import com.vezeeta.vezeetatask.domain.usecase.base.UseCaseResponse
import com.vezeeta.vezeetatask.domain.usecase.authentication.CheckIsLoggedUserUseCase
import com.vezeeta.vezeetatask.domain.usecase.authentication.LoginUseCase
import com.vezeeta.vezeetatask.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LoginViewModel constructor(
    private val loginUseCase: LoginUseCase,
    private val checkIsLoggedUserUseCase: CheckIsLoggedUserUseCase
) : BaseViewModel() {
    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    private var userMutableLiveData: MutableLiveData<LoginUser>? = null

    val isExistingUser: LiveData<Boolean>
        get() = _isExistingUser
    private val _isExistingUser by lazy { MutableLiveData<Boolean>() }

    val loggedSuccessfully: LiveData<Boolean>
        get() = _loggedSuccessfully
    private val _loggedSuccessfully by lazy { MutableLiveData<Boolean>() }

    val showProgressbar: LiveData<Boolean>
        get() = _showProgressbar
    private val _showProgressbar by lazy { MutableLiveData<Boolean>() }

    val messageData: LiveData<String>
        get() = _messageData
    private val _messageData by lazy { MutableLiveData<String>() }

    fun getUser(): MutableLiveData<LoginUser>? {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData()
        }
        return userMutableLiveData
    }

    init {
        _showProgressbar.value = false
        _loggedSuccessfully.value = false
        checkIsLoggedUserUseCase.invoke(
            null,
            object : UseCaseResponse<Boolean> {
                override fun onSuccess(result: Boolean) {
                    _isExistingUser.value = result
                }

                override fun onError(errorModel: ErrorModel?) {
                    _isExistingUser.value = false
                }
            })
    }

    fun onClick(view: View) {
        val loginUser = LoginUser(
            emailAddress.value ?: "", password.value ?: ""
        )
        userMutableLiveData?.value = loginUser
    }

    @ExperimentalCoroutinesApi
    fun login() {
        _showProgressbar.value = true

        loginUseCase.invoke(
            LoginUseCase.Params(email = emailAddress.value!!, password = password.value!!),
            object : UseCaseResponse<User> {
                override fun onSuccess(result: User) {
                    _showProgressbar.value = false
                    _loggedSuccessfully.value = true
                }

                override fun onError(errorModel: ErrorModel?) {
                    _messageData.value = errorModel?.message
                    _showProgressbar.value = false
                }
            })
    }
}