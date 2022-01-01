package com.cipa.cipamerchant.data.ServiceData

data class PayBillRequest(
    val Amount: Double,
    val BillNumber: String,
    val CreditID: Int,
    val DelivererID: Int,
    val Title: String
)