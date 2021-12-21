package com.cipa.cipamerchant.listener

interface  ViewModelListener {
    fun showMessage(message :String )
    fun showWaiting()
    fun closeWaiting()
}