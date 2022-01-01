package com.cipa.cipamerchant.data.ServiceData

import com.google.gson.annotations.SerializedName

data class PayBillResponse(
    @SerializedName("ChargAmount") val chargAmount: Double,
    @SerializedName("CreditBDM") val credit: Credit,
    @SerializedName("IsSuccessfully") val isSuccessfully: Boolean,
    @SerializedName("msg")val msg: String
)