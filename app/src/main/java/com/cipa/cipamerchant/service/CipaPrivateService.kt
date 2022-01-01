package com.cipa.cipamerchant.service

import com.cipa.cipamerchant.data.ServiceData.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CipaPrivateService {
    @POST("/api/MerchantCharge")
    fun MerchantCharge(@Body request: CreditChargeRequest): Call<Credit>

    @POST("/api/MerchantPayBill")
    fun PayBill(@Body request: PayBillRequest): Call<PayBillResponse>

    @GET("/api/GetDeliverBySupplier/{supplierId}")
    fun GetDeliverBySupplier(@Path("supplierId") supplierId:Int): Call<GetDriversResponse>
}