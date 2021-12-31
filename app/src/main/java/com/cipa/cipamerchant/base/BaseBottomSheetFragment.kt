package com.cipa.cipamerchant.base

import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.cipa.cipamerchant.data.model.MessageDialogData
import com.cipa.cipamerchant.ui.component.Dialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment<T:BaseViewModel>(private val modelClass: Class<T>) : BottomSheetDialogFragment() {
    abstract fun  OnAction(type: BaseViewModel.ActionType)
    lateinit var  waithDialog : SweetAlertDialog
    lateinit var  viewModel: T;
    fun showMessage(message: MessageDialogData) {
        Dialog(requireContext()).getMessageDialog(message.title,message.message, message.type).show()
    }

    fun showWaiting() {
        waithDialog.show();
    }

    fun closeWaiting() {
        waithDialog.dismiss()
    }

    fun initFragment() {
        viewModel =
            ViewModelProvider(this).get(modelClass)
        viewModel.action.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                t -> OnAction(t)
        })
        waithDialog =  Dialog(requireContext()).getWaitDialog()
    }
}