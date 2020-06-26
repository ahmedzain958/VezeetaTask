package com.vezeeta.vezeetatask.presentation.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vezeeta.vezeetatask.domain.model.ErrorModel
import com.vezeeta.vezeetatask.domain.model.User
import com.vezeeta.vezeetatask.domain.usecase.base.UseCaseResponse
import com.vezeeta.vezeetatask.domain.usecase.login.LoginUseCase
import com.vezeeta.vezeetatask.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class LoginViewModel constructor(private val loginUseCase: LoginUseCase) : BaseViewModel() {
    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    private var userMutableLiveData: MutableLiveData<LoginUser>? = null

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
                }

                override fun onError(errorModel: ErrorModel?) {
                    _messageData.value = errorModel?.message
                    _showProgressbar.value = false
                }
            })
    }
}