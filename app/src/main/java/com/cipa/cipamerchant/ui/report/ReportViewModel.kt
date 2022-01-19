package com.cipa.cipamerchant.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.ServiceData.MCUser
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.memory.MemoryData

class ReportViewModel :BaseViewModel() {

    var reports: MutableLiveData<List<String>>  = MutableLiveData()
    fun handleFormLoad() {
        var reps:List<String> = listOf("گزارش خرید","گزارش فروش" , "گزارش بدهی ها" , "گزارش بستانکاری ها")

        reports.postValue(reps)
    }
}