package com.vezeeta.vezeetatask.presentation.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.vezeeta.vezeetatask.R
import com.vezeeta.vezeetatask.databinding.LoginFragmentBinding
import com.vezeeta.vezeetatask.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginFragmentBinding>() {
    private val viewModel by viewModel<LoginViewModel>()
    private lateinit var binding: LoginFragmentBinding
    override fun getLayoutId(): Int {
        return R.layout.login_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        binding.model = viewModel
        //todo remove
        viewModel.emailAddress.value = "test.test@vezeeta.com"
        viewModel.password.value = "123456"
        //
        viewModel.getUser()?.observe(viewLifecycleOwner,
            Observer<LoginUser> { loginUser ->
                if (TextUtils.isEmpty(loginUser.strEmailAddress)) {
                    context?.resources?.getString(R.string.no_email_message)?.let {
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
        /*Navigation.findNavController(binding.buttonLogin)
                .navigate(R.id.action_loginFragment_to_offersListFragment)*/
    }
}