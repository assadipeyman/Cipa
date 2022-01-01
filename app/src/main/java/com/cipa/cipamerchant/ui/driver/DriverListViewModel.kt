package com.cipa.cipamerchant.ui.driver

import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.businessData.BMarket
import androidx.lifecycle.MutableLiveData
import cn.pedant.SweetAlert.SweetAlertDialog
import com.cipa.cipamerchant.data.ServiceData.*
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.data.model.MessageDialogData
import com.cipa.cipamerchant.memory.MemoryData
import com.cipa.cipamerchant.service.CipaPrivateService
import com.cipa.cipamerchant.service.RetroServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DriverListViewModel()  : BaseViewModel() {
    var driverList: MutableLiveData<List<Driver>> = MutableLiveData()
    var title : MutableLiveData<String> = MutableLiveData()
    private var marketId : Int=0
        get() { return field}
    private var supplierId : Int=0
        get() { return field}
    var message: MutableLiveData<MessageDialogData> = MutableLiveData()

    fun handleFormLoad(marketId:Int , supplierId:Int ) {
        this.marketId = marketId
        this.supplierId = supplierId
        updtateUiFromMemory()
    }

    fun updtateUiFromMemory() {
        val supplier: BSupplier? = MemoryData.getSupplier(marketId, supplierId)
        if (supplier!!.drivers != null && supplier!!.drivers!!.size > 0) {
            driverList.postValue(supplier!!.drivers)
            title.postValue(supplier.name)
        }
        else
            fetchDrivers()
    }
    fun fetchDrivers()
    {
        action.postValue(BaseViewModel.ActionType.SHOW_WAIT)
        val request = RetroServiceBuilder.buildService(CipaPrivateService::class.java)

        val call =
            request.GetDeliverBySupplier(supplierId)
        call.enqueue(object : Callback<GetDriversResponse> {
            override fun onResponse(
                call: Call<GetDriversResponse>,
                response: Response<GetDriversResponse>
            ) {
                action.postValue(BaseViewModel.ActionType.CLOSE_WAIT)
                if (response.code() == 200 && response.body() != null) {
                    MemoryData.updateDriverList(marketId, supplierId, response.body()!!.toList())
                    updtateUiFromMemory()
                } else if (response.code() == 400 && response.errorBody() != null) {
                    message.postValue(
                        MessageDialogData(
                            SweetAlertDialog.ERROR_TYPE,
                            "شارژ",
                            "دریافت لیست رانندگان با خطا مواجه شد. لطفا دوباره تلاش نمایید"
                        )
                    )
                }
            }

            override fun onFailure(call: Call<GetDriversResponse>, t: Throwable) {
                action.postValue(BaseViewModel.ActionType.CLOSE_WAIT)
                message.postValue(
                    MessageDialogData(
                        SweetAlertDialog.ERROR_TYPE,
                        "شارژ",
                        "دریافت لیست رانندگان با خطا مواجه شد. لطفا دوباره تلاش نمایید"
                    )
                )
            }
        })
    }

}