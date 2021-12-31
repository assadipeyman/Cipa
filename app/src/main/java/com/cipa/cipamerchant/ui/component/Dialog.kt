package com.cipa.cipamerchant.ui.component

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.webkit.ServiceWorkerWebSettings
import cn.pedant.SweetAlert.SweetAlertDialog
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.data.model.MessageDialogData

class Dialog(val context: Context)  {
    var waithDialog : SweetAlertDialog? = null
    fun getMessageDialog(title:String , message:String , type:Int) : SweetAlertDialog{
        return SweetAlertDialog(context, type)
            .setTitleText("سیپا")
            .setContentText(message)
            .setConfirmText("باشه")
    }
    fun getWaitDialog() : SweetAlertDialog{
         val waithDialog  : SweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
        waithDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        waithDialog.setTitleText("در حال بارگذاری");
        waithDialog.setCancelable(false);
        return waithDialog
    }
    fun initWaitDialog(){
        if(waithDialog==null)
            waithDialog = getWaitDialog()
    }
    fun showMessage(message: MessageDialogData) {
        Dialog(context).getMessageDialog(message.title, message.message,message.type).show()
    }
    fun showWaiting() {
        initWaitDialog()
        waithDialog?.show();
    }

    fun closeWaiting() {
        initWaitDialog()
        if(waithDialog!!.isShowing)
            waithDialog?.dismiss()
    }
}