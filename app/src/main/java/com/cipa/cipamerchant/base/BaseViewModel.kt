package com.cipa.cipamerchant.base

import androidx.lifecycle.ViewModel
import com.cipa.cipamerchant.listener.ViewModelListener

open class BaseViewModel(val listener: ViewModelListener) :ViewModel() {

}