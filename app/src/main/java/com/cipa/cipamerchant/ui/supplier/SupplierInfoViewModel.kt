package com.cipa.cipamerchant.ui.supplier

import android.R
import android.app.AlertDialog
import android.net.DnsResolver
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.businessData.BMarket
import androidx.lifecycle.MutableLiveData
import com.cipa.cipamerchant.data.ServiceData.*
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.memory.MemoryData
import com.cipa.cipamerchant.service.CipaPrivateService
import com.cipa.cipamerchant.service.CipaPublicService
import com.cipa.cipamerchant.service.RetroServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.DialogInterface
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.cipa.cipamerchant.data.model.MessageDialogData

class SupplierInfoViewModel()  : BaseViewModel() {
    var supplier: MutableLiveData<BSupplier> = MutableLiveData()
    var message: MutableLiveData<MessageDialogData> = MutableLiveData()
    var marketId: Int = 0
    var supplierId: Int = 0
    fun handleFormLoad(marketId: Int, supplierId: Int) {
        this.marketId = marketId
        this.supplierId = supplierId
        uiUpdate()
    }

    fun uiUpdate() {
        supplier.postValue(MemoryData.getSupplier(marketId, supplierId))
    }

}
