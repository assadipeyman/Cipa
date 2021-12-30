package com.cipa.cipamerchant.ui.Market

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cipa.cipamerchant.adaptor.MarketAdapter
import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.databinding.ActivityMainBinding
import com.cipa.cipamerchant.databinding.ActivityMarketList1Binding
import com.cipa.cipamerchant.databinding.ActivityMarketListBinding
import com.cipa.cipamerchant.ui.login.LoginViewModel
import com.cipa.cipamerchant.utils.StringUtils

class MarketListActivity  : BaseActivity<MarketListViewModel>(MarketListViewModel::class.java) {
    private lateinit var binding: ActivityMarketListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitViewModel()

        viewModel.handleFormLoad()
        viewModel.marketList?.observe(this,
            Observer { t ->
                binding.rvMarket.layoutManager = LinearLayoutManager(this)

                var adaptor: MarketAdapter = MarketAdapter(t, { position, market -> })
                binding.rvMarket.adapter = adaptor
            }
        )
        viewModel.userData.observe(this , Observer { t->
            binding.tvTotalDebt.text = StringUtils.toCurrencyFormat(12500000.0)  //t.sumRealDebt.toString()
            binding.tvMaxCredit.text =StringUtils.toCurrencyFormat(12500000000.0   )  //t.maxCredit.toString()
        })
    }

    override fun OnAction(type: BaseViewModel.ActionType) {
        TODO("Not yet implemented")
    }
}