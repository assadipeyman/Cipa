package com.cipa.cipamerchant.ui.component

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.cipa.cipamerchant.R

class WaitingDialog(context: Context) : AlertDialog(context) {
    init {
        setCancelable(false)
        setView(LayoutInflater.from(context).inflate(R.layout.layout_progress, null))
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}