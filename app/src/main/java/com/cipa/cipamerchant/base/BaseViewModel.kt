package com.cipa.cipamerchant.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cipa.cipamerchant.listener.ViewModelListener

abstract class BaseViewModel() :ViewModel() {
     var action: MutableLiveData<ActionType> = MutableLiveData()

     enum class ActionType {
          SHOW_WAIT,
          CLOSE_WAIT,
          SHOW_MARKET_ACTIVITY,
          SHOW_SUPPLIER_ACTIVITY
     }
}