package com.cipa.cipamerchant.data.ServiceData

import com.google.gson.annotations.SerializedName

data class MerchantBDM(
    @SerializedName("Address")  val address: String,
    @SerializedName("CreateDate")  val createDate: String,
    @SerializedName("CreatorId")  val creatorId: Int?,
    @SerializedName("Id")  val id: Int,
    @SerializedName("IsActive")  val isActive: Boolean,
    @SerializedName("IsDeleted")  val sDeleted: Boolean,
    @SerializedName("Name")  val name: String,
    @SerializedName("Tell")  val tell: String,
    @SerializedName("UpdateDate")  val updateDate: String,
    @SerializedName("cUserId")  val cUserId: Int
)