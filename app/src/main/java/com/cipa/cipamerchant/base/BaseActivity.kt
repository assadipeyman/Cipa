package com.cipa.cipamerchant.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.cipa.cipamerchant.listener.ViewModelListener
import com.cipa.cipamerchant.ui.component.WaitingDialog

open class BaseActivity : AppCompatActivity() , ViewModelListener {
      var  dialog: AlertDialog? = null

    override fun showMessage(message: String) {
    }
    fun initDialog(){
        dialog = WaitingDialog(this)
    }
    override fun showWaiting() {
        if(dialog==null)
            initDialog()
        dialog?.show()
        
    }

    override fun closeWaiting() {
        if(dialog==null)
            initDialog()
        dialog?.dismiss()
    }
}