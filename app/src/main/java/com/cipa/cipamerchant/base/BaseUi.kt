package com.cipa.cipamerchant.base

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.cipa.cipamerchant.data.model.MessageDialogData
import com.cipa.cipamerchant.ui.component.Dialog

interface BaseUi{
    fun showMessage(message: MessageDialogData)
    fun showWaiting()
    fun closeWaiting()
}