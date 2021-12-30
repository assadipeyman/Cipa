package com.cipa.cipamerchant.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cipa.cipamerchant.ui.component.WaitingDialog

abstract class BaseActivity<T:BaseViewModel>(private val modelClass: Class<T>) : AppCompatActivity()  {
     abstract fun  OnAction(type: BaseViewModel.ActionType)
      var  dialog: AlertDialog? = null
    lateinit var  viewModel: T;
    fun showMessage(message: String) {
    }

    fun initDialog(){
        dialog = WaitingDialog(this)
    }
     fun showWaiting() {
        if(dialog==null)
            initDialog()
        dialog?.show()
        
    }

     fun closeWaiting() {
        if(dialog==null)
            initDialog()
        dialog?.dismiss()
    }

    fun InitViewModel() {
        viewModel =
            ViewModelProvider(this).get(modelClass)
        viewModel.action.observe(this, Observer { t->
            OnAction(t)
        })
    }

}