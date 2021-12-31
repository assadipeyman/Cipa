package com.cipa.cipamerchant.memory

import com.cipa.cipamerchant.data.ServiceData.*
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.google.gson.annotations.SerializedName

object MemoryData {
    @SerializedName("User")
    var user: MCUser? = null
    @SerializedName("Markets")
    lateinit var markets: ArrayList<BMarket>
    fun setData(data: McLoginResponse?) {
        markets = arrayListOf();
        user = null
        if (data == null) return
        user = data!!.mCUser
        data!!.markets.forEach {
            var suppliers: ArrayList<BSupplier> = ArrayList()
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
                        market.getCredit(it.id),
                        null
                    )
                )
            }
            markets?.add(BMarket(it.merchantBDM, suppliers))
        }
    }

    fun getSumDebtOfMarket(marketId: Int): Long {
        var sumDebt: Double = 0.0
        val market: BMarket? = getMarket(marketId)
        if (market != null)
            market.supliers.forEach { t ->
                sumDebt += t.credit!!.debt
            }
        return sumDebt.toLong();
    }

    fun getMarket(marketId: Int): BMarket? {
        return markets.find<BMarket> { bMarket -> bMarket.merchantBDM.id == marketId }
    }

    fun getSupplier(marketId: Int, supplierId: Int): BSupplier? {
        markets.forEach { bMarket ->
            if (bMarket.merchantBDM.id == marketId)
                bMarket.supliers.forEach { bSupplier ->
                    if (bSupplier.id == supplierId) return bSupplier
                }
        }
        return null
    }

    fun updateDriverList(marketId: Int, supplierId: Int , drivers:  List<Driver>) {
        markets.forEach { bMarket ->
            if (bMarket.merchantBDM.id == marketId)
                bMarket.supliers.forEach { bSupplier ->
                    if (bSupplier.id == supplierId) bSupplier.drivers = drivers
                }
        }
    }
    fun getToken():String{
        return user!!.tTKK + ":" + user!!.userName
    }
    fun updateCredit(creditId : Int , credit: Credit){
        markets.forEach { bMarket ->
            bMarket.supliers.forEach { bSupplier ->
                if (bSupplier.credit!!.id == creditId) bSupplier.credit = credit
            }
        }
    }
}
