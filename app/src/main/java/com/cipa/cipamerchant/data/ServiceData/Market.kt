package com.cipa.cipamerchant.data.ServiceData

import com.google.gson.annotations.SerializedName

data class Market(
    @SerializedName("Credits")  val credits: List<Credit>,
    @SerializedName("MerchantBDM")  val merchantBDM: MerchantBDM,
    @SerializedName("Suppliers")  val suppliers: List<Supplier>
)
{
    fun getCredit(supplierId:Int): Credit? {
        val x = credits.filter { it.cSupplierId == supplierId }
        if (x.size == 0)
            return null;
        return x[0]
    }

}