package com.cipa.cipamerchant.data.ServiceData

import com.google.gson.annotations.SerializedName

data class MCUser(
    @SerializedName("Address")  val address: String?,
    @SerializedName("CodeMelli")  val codeMelli: String,
    @SerializedName("CreateDate")  val createDate: String,
    @SerializedName("CreatorId")  val creatorId: Int?,
    @SerializedName("FirstName")  val firstName: String,
    @SerializedName("Id")  val id: Int,
    @SerializedName("IsActive")  val isActive: Boolean,
    @SerializedName("IsDeleted")  val isDeleted: Boolean,
    @SerializedName("LastName")  val lastName: String,
    @SerializedName("MaxCredit")  val maxCredit: Double,
    @SerializedName("Mobile")  val mobile: String,
    @SerializedName("SumRealDebt")  val sumRealDebt: Double,
    @SerializedName("TTKK")  val tTKK: String,
    @SerializedName("Tell")  val tell: String,
    @SerializedName("UpdateDate")  val updateDate: String,
    @SerializedName("UserName")  val userName: String,
    @SerializedName("UserType")  val userType: Int,
    @SerializedName("pass")  val pass: String
)