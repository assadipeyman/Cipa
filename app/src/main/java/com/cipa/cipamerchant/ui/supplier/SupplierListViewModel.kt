package com.cipa.cipamerchant.ui.supplier

import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.businessData.BMarket
import androidx.lifecycle.MutableLiveData
import com.cipa.cipamerchant.data.ServiceData.MCUser
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.memory.MemoryData


class SupplierListViewModel()  : BaseViewModel() {
    var supplierList: MutableLiveData<ArrayList<BSupplier>> = MutableLiveData()

    fun handleFormLoad(maerketId: Int) {
        supplierList.postValue(MemoryData.getMarket(maerketId)!!.supliers)
    }
}