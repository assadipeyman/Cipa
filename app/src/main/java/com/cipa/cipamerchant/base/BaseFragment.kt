package com.cipa.cipamerchant.base

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.cipa.cipamerchant.data.model.MessageDialogData
import com.cipa.cipamerchant.ui.component.Dialog

abstract class BaseFragment<T:BaseViewModel>(private val modelClass: Class<T>) : Fragment() ,
    BaseUi {
    lateinit var dialog : Dialog
    abstract fun  OnAction(type: BaseViewModel.ActionType)

    lateinit var  viewModel: T;

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    fun initFragment() {
        viewModel =
            ViewModelProvider(this).get(modelClass)
        viewModel.action.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                t -> OnAction(t)
        })
         dialog = Dialog(requireContext())
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