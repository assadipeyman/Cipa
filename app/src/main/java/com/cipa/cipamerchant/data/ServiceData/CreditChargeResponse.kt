package com.cipa.cipamerchant.data.ServiceData

data class CreditChargeResponse(
    val BalanceAmount: Double,
    val CreateDate: String,
    val CreatorId: Int,
    val CreditAmount: Double,
    val Debt: Double,
    val Duration: Double,
    val GiftRate: Double,
    val Id: Int,
    val InitPayable: Double,
    val IsActive: Boolean,
    val IsDeleted: Boolean,
    val NoPenaltyDuration: Double,
    val OperationFeeRate: Double,
    val PenaltyRate: Double,
    val StartingDateTime: String,
    val UpdateDate: String,
    val cAccId: Int,
    val cMerchantId: Int,
    val cProviderId: Int,
    val cSupplierId: Int
)