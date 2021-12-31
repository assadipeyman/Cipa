package com.cipa.cipamerchant.ui.supplier

import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.businessData.BMarket
import androidx.lifecycle.MutableLiveData
import com.cipa.cipamerchant.data.ServiceData.MCUser
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.memory.MemoryData


class SupplierListViewModel()  : BaseViewModel() {
    var supplierList: MutableLiveData<ArrayList<BSupplier>> = MutableLiveData()
    var title : MutableLiveData<String> = MutableLiveData()

    fun handleFormLoad(maerketId: Int) {
        val bmarket : BMarket? = MemoryData.getMarket(maerketId)
        supplierList.postValue(bmarket!!.supliers)
        title.postValue(bmarket.merchantBDM.name)
    }
}