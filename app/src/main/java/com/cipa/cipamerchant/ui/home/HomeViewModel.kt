package com.cipa.cipamerchant.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.ServiceData.MCUser
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.memory.MemoryData

class HomeViewModel :BaseViewModel() {

    var marketList: MutableLiveData<ArrayList<BMarket>>  = MutableLiveData()
    var userData : MutableLiveData<MCUser> = MutableLiveData()

    fun handleFormLoad() {
        marketList.postValue(MemoryData.markets)
        userData.postValue(MemoryData.user)
    }
}