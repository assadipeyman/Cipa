package com.cipa.cipamerchant.ui.credit

import android.R
import android.app.AlertDialog
import android.net.DnsResolver
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.businessData.BMarket
import androidx.lifecycle.MutableLiveData
import com.cipa.cipamerchant.data.ServiceData.*
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.memory.MemoryData
import com.cipa.cipamerchant.service.CipaPrivateService
import com.cipa.cipamerchant.service.CipaPublicService
import com.cipa.cipamerchant.service.RetroServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.DialogInterface
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.cipa.cipamerchant.data.model.MessageDialogData

class CreditChargeViewModel()  : BaseViewModel() {

    var message: MutableLiveData<MessageDialogData> = MutableLiveData()
    var state: MutableLiveData<Boolean> = MutableLiveData()
    var creditId: Int = 0
    fun handleFormLoad(creditId:Int) {
        this.creditId = creditId
        uiUpdate()
    }

    fun uiUpdate() {
    }

    fun charge(amount: Number) {
        action.postValue(BaseViewModel.ActionType.SHOW_WAIT)
        val request = RetroServiceBuilder.buildService(CipaPrivateService::class.java)

        val call =
            request.MerchantCharge(CreditChargeRequest(amount as Int, creditId))
        call.enqueue(object : Callback<Credit> {
            override fun onResponse(
                call: Call<Credit>,
                response: Response<Credit>
            ) {
                action.postValue(BaseViewModel.ActionType.CLOSE_WAIT)
                if (response.code() == 200 && response.body() != null) {
                    MemoryData.updateCredit(response.body()!!.id, response.body()!!)
                    uiUpdate();
                    message.postValue(
                        MessageDialogData(
                            SweetAlertDialog.SUCCESS_TYPE,
                            "شارژ",
                            "َشارژ اعتبار با موفقیت انجام شد"
                        )
                    )
                    state.postValue(true)
                } else if (response.code() == 400 && response.errorBody() != null && response.errorBody()!!
                        .string()!!
                        .contains("You can not charge your account multiple times in less than a minute")
                ) {
                    message.postValue(
                        MessageDialogData(
                            SweetAlertDialog.ERROR_TYPE,
                            "شارژ",
                            "برای انجام شارژ حداقل باید 1 دقیقه از آخرین شارژ شما گذشته باشد. لطفا کمی صبر نمایید"
                        )
                    )
                    state.postValue(false)
                }
            }

            override fun onFailure(call: Call<Credit>, t: Throwable) {
                action.postValue(BaseViewModel.ActionType.CLOSE_WAIT)
                message.postValue(
                    MessageDialogData(
                        SweetAlertDialog.ERROR_TYPE,
                        "شارژ",
                        "شارژ اعتبار با خطا مواجه شد. لطفا دوباره تلاش نمایید"
                    )
                )
                state.postValue(false)
            }
        })
    }
}
