package com.cipa.cipamerchant.base

import android.graphics.Color

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.cipa.cipamerchant.data.model.MessageDialogData
import com.cipa.cipamerchant.ui.component.Dialog

abstract class BaseActivity<T:BaseViewModel>(private val modelClass: Class<T>) : AppCompatActivity() ,BaseUi {
     abstract fun  OnAction(type: BaseViewModel.ActionType)
    lateinit var dialog : Dialog ;

    lateinit var  viewModel: T;

    fun InitActivity() {
        viewModel =
            ViewModelProvider(this).get(modelClass)
        viewModel.action.observe(this, Observer { t->
            OnAction(t)
        })
        dialog = Dialog(this)
    }


    override fun showWaiting() {
        dialog.showWaiting()
    }

    override fun closeWaiting() {
        dialog.closeWaiting()
    }

    override fun showMessage(message: MessageDialogData) {
        dialog.showMessage(message)
    }
}