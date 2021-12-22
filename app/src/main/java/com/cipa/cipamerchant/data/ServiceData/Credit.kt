package com.cipa.cipamerchant.data.ServiceData

import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("BalanceAmount") val balanceAmount: Double,
    @SerializedName("CreateDate") val createDate: String,
    @SerializedName("CreatorId") val creatorId: Int,
    @SerializedName("CreditAmount") val creditAmount: Double,
    @SerializedName("Debt") val debt: Double,
    @SerializedName("Duration") val duration: Double,
    @SerializedName("GiftRate") val giftRate: Double,
    @SerializedName("Id") val id: Int,
    @SerializedName("InitPayable") val initPayable: Double,
    @SerializedName("IsActive") val isActive: Boolean,
    @SerializedName("IsDeleted") val isDeleted: Boolean,
    @SerializedName("NoPenaltyDuration") val noPenaltyDuration: Double,
    @SerializedName("OperationFeeRate") val operationFeeRate: Double,
    @SerializedName("PenaltyRate") val penaltyRate: Double,
    @SerializedName("StartingDateTime") val startingDateTime: String,
    @SerializedName("UpdateDate") val updateDate: String,
    @SerializedName("cAccId") val cAccId: Int,
    @SerializedName("cMerchantId") val cMerchantId: Int,
    @SerializedName("cProviderId") val cProviderId: Int,
    @SerializedName("cSupplierId") val cSupplierId: Int
)