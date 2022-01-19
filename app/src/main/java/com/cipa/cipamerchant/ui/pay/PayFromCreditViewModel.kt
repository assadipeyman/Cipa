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
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat

class PayFromCreditViewModel()  : BaseViewModel() {

    var message: MutableLiveData<MessageDialogData> = MutableLiveData()
    var state: MutableLiveData<Boolean> = MutableLiveData()
    var creditId: Int = 0
    fun handleFormLoad(creditId:Int) {
        this.creditId = creditId
    }

    fun pay(amount: Number , invoicenumber: String , driverId :Int) {
        action.postValue(BaseViewModel.ActionType.SHOW_WAIT)
        val request = RetroServiceBuilder.buildService(CipaPrivateService::class.java)

        val call =
            request.PayBill(PayBillRequest(amount.toDouble(),invoicenumber,creditId,driverId ,"خرید"))
        call.enqueue(object : Callback<PayBillResponse> {
            override fun onResponse(
                call: Call<PayBillResponse>,
                response: Response<PayBillResponse>
            ) {
                action.postValue(BaseViewModel.ActionType.CLOSE_WAIT)
                if (response.code() == 200 && response.body() != null) {
                    if(response.body()!!.isSuccessfully) {
                        MemoryData.updateCredit(
                            response.body()!!.credit.cMerchantId,
                            response.body()!!.credit.id,
                            response.body()!!.credit
                        )
                        message.postValue(
                            MessageDialogData(
                                SweetAlertDialog.SUCCESS_TYPE,
                                "خرید",
                                "َخرید با موفقیت انجام شد"
                            )
                        )
                        state.postValue(true)
                    }
                    else
                    {
                        message.postValue(
                            MessageDialogData(
                                SweetAlertDialog.ERROR_TYPE,
                                "خرید",
                                "اعتبار شما برای انجام خرید کافی نیست. برای خرید حداقل نیاز به شارژ مبلغ زیر دارید \n " + response.body()!!.chargAmount.withCurrencyFormat
                            )
                        )
                        state.postValue(false)
                    }
                } else if (response.code() == 400 && response.errorBody() != null && response.errorBody()!!
                        .string()!!
                        .contains("You can not charge your account multiple times in less than a minute")
                ) {
                    message.postValue(
                        MessageDialogData(
                            SweetAlertDialog.ERROR_TYPE,
                            "خرید",
                            "برای انجام شارژ حداقل باید 1 دقیقه از آخرین شارژ شما گذشته باشد. لطفا کمی صبر نمایید"
                        )
                    )
                    state.postValue(false)
                }
            }

            override fun onFailure(call: Call<PayBillResponse>, t: Throwable) {
                action.postValue(BaseViewModel.ActionType.CLOSE_WAIT)
                message.postValue(
                    MessageDialogData(
                        SweetAlertDialog.ERROR_TYPE,
                        "رانندگان",
                        "شارژ اعتبار با خطا مواجه شد. لطفا دوباره تلاش نمایید"
                    )
                )
                state.postValue(false)
            }
        })
    }
}
