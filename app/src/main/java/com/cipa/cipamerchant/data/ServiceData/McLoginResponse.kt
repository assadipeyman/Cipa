package com.cipa.cipamerchant.data.ServiceData

import com.google.gson.annotations.SerializedName

data class McLoginResponse(
    @SerializedName("MCUser")val mCUser: MCUser,
    @SerializedName("Markets")val markets: List<Market>
)