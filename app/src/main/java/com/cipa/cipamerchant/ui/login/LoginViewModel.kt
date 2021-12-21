package com.cipa.cipamerchant.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.listener.ViewModelListener

class LoginViewModel(listener: ViewModelListener)  : BaseViewModel(listener) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun handleLoginClick(){
            listener.showWaiting()
    }

}