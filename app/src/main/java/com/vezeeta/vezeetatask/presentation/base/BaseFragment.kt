package com.vezeeta.vezeetatask.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), CoroutineScope {

    private lateinit var mViewDataBinding: T
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding.root
    }

    /**
     * @return layout resource id
     * LayoutRes annotation is added because layout resource integer value is expected.
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    fun showSnackBar(message: String?, length: Int) {
        Snackbar.make(mViewDataBinding.root, message.toString(), length).show()
    }

}