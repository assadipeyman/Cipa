package com.cipa.cipamerchant.ui.market

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cipa.cipamerchant.adaptor.MarketAdapter
import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.databinding.ActivityMarketListBinding
import com.cipa.cipamerchant.utils.StringUtils
import android.R.attr.key
import com.cipa.cipamerchant.ui.supplier.SupplierListActivity
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat


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

                var adaptor = MarketAdapter(t,
                    {
                            position, market ->
                        val intent = Intent(this, SupplierListActivity::class.java)
                        val mBundle = Bundle()
                        mBundle.putInt("marketid", market.merchantBDM.id)
                        intent.putExtras(mBundle)
                        startActivity(intent)
                    })
                binding.rvMarket.adapter = adaptor
            }
        )
        viewModel.userData.observe(this, Observer { t ->
            binding.tvTotalDebt.text =
                12500000.0.withCurrencyFormat  //t.sumRealDebt.toString()
            binding.tvMaxCredit.text =
                12500000000.0.withCurrencyFormat  //t.maxCredit.toString()
        })
    }

    override fun OnAction(type: BaseViewModel.ActionType) {
        TODO("Not yet implemented")
    }
}