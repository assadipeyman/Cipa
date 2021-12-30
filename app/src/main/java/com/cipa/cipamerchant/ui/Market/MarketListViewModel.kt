package com.cipa.cipamerchant.ui.Market

import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.listener.ViewModelListener
import androidx.lifecycle.MutableLiveData
import com.cipa.cipamerchant.data.ServiceData.MCUser
import com.cipa.cipamerchant.memory.MemoryData


class MarketListViewModel()  : BaseViewModel()  {
    var marketList: MutableLiveData<ArrayList<BMarket>>  = MutableLiveData()
    var userData : MutableLiveData<MCUser> = MutableLiveData()

    fun handleFormLoad() {
        marketList.postValue(MemoryData.markets)
        userData.postValue(MemoryData.user)
    }

    fun onMarketClick(position : Int, market:BMarket) {

    }
}