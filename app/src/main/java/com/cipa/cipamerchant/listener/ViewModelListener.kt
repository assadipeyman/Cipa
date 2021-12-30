package com.cipa.cipamerchant.listener

import com.cipa.cipamerchant.base.BaseViewModel
import java.util.*

interface  ViewModelListener {
    fun OnAction(type: BaseViewModel.ActionType)
}