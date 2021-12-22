package com.cipa.cipamerchant.memory

import com.cipa.cipamerchant.data.ServiceData.MCUser
import com.cipa.cipamerchant.data.ServiceData.Market
import com.cipa.cipamerchant.data.ServiceData.McLoginResponse
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.google.gson.annotations.SerializedName

object MemoryData{
   @SerializedName("User") lateinit var user: MCUser
    @SerializedName("Markets") var markets: ArrayList<BMarket> = ArrayList()
    fun setData(data: McLoginResponse?){
        if (data==null) return
        user = data!!.mCUser
        data!!.markets.forEach {
            var suppliers: ArrayList<BSupplier> =ArrayList()
            val market: Market = it;
            it.suppliers.forEach {

                suppliers.add(
                    BSupplier(
                        it.createDate,
                        it.creatorId,
                        it.id,
                        it.isDeleted,
                        it.name,
                        it.updateDate,
                        market.getCredit(it.id)
                    )
                )
            }
            markets.add(BMarket(it.merchantBDM, suppliers))
        }
    }
}