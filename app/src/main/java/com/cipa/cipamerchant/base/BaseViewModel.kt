package com.cipa.cipamerchant.base

import androidx.lifecycle.ViewModel
import com.cipa.cipamerchant.listener.ViewModelListener

abstract class BaseViewModel() :ViewModel() {
     lateinit var listener: ViewModelListener
     abstract fun setViewModelListener(_listener: ViewModelListener);
}