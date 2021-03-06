package com.vezeeta.vezeetatask.presentation.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.databinding.LoginFragmentBinding
import com.vezeeta.vezeetatask.presentation.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class LoginFragment : BaseFragment<LoginFragmentBinding>() {
    @ExperimentalCoroutinesApi
    private val viewModel by viewModel<LoginViewModel>()
    private lateinit var binding: LoginFragmentBinding
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun getLayoutId(): Int {
        return R.layout.login_fragment
    }

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.isExistingUser.observe(viewLifecycleOwner, Observer { isExist ->
            if (isExist) {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_loginFragment_to_offersListFragment)
            }
        })
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        binding.model = viewModel
        viewModel.getUser()?.observe(viewLifecycleOwner,
            Observer<LoginUser> { loginUser: LoginUser ->
                validateUserInput(view, loginUser)
            })
        viewModel.showProgressbar.observe(viewLifecycleOwner, Observer { isVisible ->
            if (isVisible) binding.progressbar.show() else binding.progressbar.hide()
        })
        viewModel.messageData.observe(viewLifecycleOwner, Observer { message ->
            Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        })
        viewModel.loggedSuccessfully.observe(viewLifecycleOwner, Observer { isLogged ->
            if (isLogged) {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_loginFragment_to_offersListFragment)
            }
        })

    }

    @ExperimentalCoroutinesApi
    private fun validateUserInput(
        view: View,
        loginUser: LoginUser
    ) {
        if (TextUtils.isEmpty(loginUser.strEmailAddress)) {
            context?.resources?.getString(R.string.no_email_message)?.let {
                binding.edittextPassword.error = null
                binding.edittextEmail.error = it
                Snackbar.make(
                    view,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
            binding.edittextEmail.requestFocus()
        } else if (!loginUser.isEmailValid) {
            context?.resources?.getString(R.string.not_valid_email_message)?.let {
                binding.edittextPassword.error = null
                binding.edittextEmail.error = it
                Snackbar.make(
                    view,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
            binding.edittextEmail.requestFocus()
        } else if (TextUtils.isEmpty(loginUser.strPassword)) {
            context?.resources?.getString(R.string.no_password_message)?.let {
                binding.edittextEmail.error = null
                binding.edittextPassword.error = it
                Snackbar.make(
                    view,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
            binding.edittextPassword.requestFocus()
        } else if (!loginUser.isPasswordLengthGreaterThan5) {
            context?.resources?.getString(R.string.not_valid_password_message)?.let {
                binding.edittextEmail.error = null
                binding.edittextPassword.error = it
                Snackbar.make(
                    view,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
            binding.edittextPassword.requestFocus()
        } else {
            viewModel.login()
        }
    }
}