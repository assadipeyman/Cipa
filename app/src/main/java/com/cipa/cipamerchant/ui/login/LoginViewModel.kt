package com.cipa.cipamerchant.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.ServiceData.McLoginRequest
import com.cipa.cipamerchant.data.ServiceData.McLoginResponse
import com.cipa.cipamerchant.data.businessData.BMarket
import com.cipa.cipamerchant.listener.ViewModelListener
import com.cipa.cipamerchant.memory.MemoryData
import com.cipa.cipamerchant.service.CipaService
import com.cipa.cipamerchant.service.RetroServiceBuilder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel()  : BaseViewModel() ,   Callback<McLoginResponse> {

    fun handleLoginClick(username: String, password: String) {
        action.postValue(ActionType.SHOW_WAIT)
        val request = RetroServiceBuilder.buildService(CipaService::class.java)

        val call = request.McLogin(McLoginRequest(username, password))
        call.enqueue(this)
    }

    override fun onResponse(call: Call<McLoginResponse>, response: Response<McLoginResponse>) {
        action.value = ActionType.CLOSE_WAIT
        MemoryData.setData(response.body())
        val g: Gson = Gson()
        Log.d("Tag json market", g.toJson(MemoryData.markets))
        action.postValue(ActionType.SHOW_MARKET_ACTIVITY)
    }

    override fun onFailure(call: Call<McLoginResponse>, t: Throwable) {
        action.postValue(ActionType.CLOSE_WAIT)
    }

}