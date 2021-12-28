package com.cipa.cipamerchant.ui.Market

import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.listener.ViewModelListener
import androidx.lifecycle.MutableLiveData
import com.cipa.cipamerchant.memory.MemoryData


class MarketListViewModel()  : BaseViewModel()  {
    var marketList: MutableLiveData<ArrayList<BMarket>>  = MutableLiveData()

    override fun setViewModelListener(_listener: ViewModelListener) {
        listener = _listener
    }

    fun handleFormLoad() {
        marketList.postValue(MemoryData.markets)
    }


    fun onMarketClick(position : Int, market:BMarket) {

    }
}