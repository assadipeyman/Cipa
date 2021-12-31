package com.cipa.cipamerchant.service

import com.cipa.cipamerchant.data.ServiceData.McLoginRequest
import com.cipa.cipamerchant.data.ServiceData.McLoginResponse
import com.cipa.cipamerchant.memory.MemoryData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface CipaPublicService {
    @POST("/api/mcLogin")
    fun McLogin(@Body request: McLoginRequest): Call<McLoginResponse>
}