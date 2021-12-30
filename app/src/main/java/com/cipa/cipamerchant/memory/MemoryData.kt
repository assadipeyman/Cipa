package com.cipa.cipamerchant.memory

import com.cipa.cipamerchant.data.ServiceData.MCUser
import com.cipa.cipamerchant.data.ServiceData.Market
import com.cipa.cipamerchant.data.ServiceData.McLoginResponse
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.google.gson.annotations.SerializedName

object MemoryData{
   @SerializedName("User")  var user: MCUser? = null
    @SerializedName("Markets")lateinit var markets: ArrayList<BMarket>
    fun setData(data: McLoginResponse?){
        markets = arrayListOf();
        user=null
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
            markets?.add(BMarket(it.merchantBDM, suppliers))
        }
    }
    fun getSumDebtOfMarket(marketId: Int) :Double{
        var sumDebt : Double = 0.0
        val market: BMarket? = getMarket(marketId)
        if(market!=null)
            market.supliers.forEach { t ->
                sumDebt += t.credit!!.debt
            }
        return sumDebt;
    }
    fun getMarket(marketId:Int): BMarket? {
        return markets.find<BMarket> { bMarket -> bMarket.merchantBDM.id == marketId }
    }
    fun  getSupplier(id: Int):BSupplier?{
        markets.forEach {
            bMarket -> bMarket.supliers.forEach { bSupplier ->  if(bSupplier.id == id) return  bSupplier }
        }
        return  null
    }
}
