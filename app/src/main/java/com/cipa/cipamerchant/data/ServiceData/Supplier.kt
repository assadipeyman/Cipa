package com.cipa.cipamerchant.data.ServiceData

import com.google.gson.annotations.SerializedName

open class Supplier(
    @SerializedName("CreateDate")   val createDate: String,
    @SerializedName("CreatorId")   val creatorId: Int?,
    @SerializedName("Id")   val id: Int,
    @SerializedName("IsDeleted")   val isDeleted: Boolean,
    @SerializedName("Name")   val name: String,
    @SerializedName("UpdateDate")   val updateDate: String
)