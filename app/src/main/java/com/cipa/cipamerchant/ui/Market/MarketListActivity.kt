package com.cipa.cipamerchant.ui.Market

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cipa.cipamerchant.adaptor.MarketAdapter
import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.databinding.ActivityMainBinding
import com.cipa.cipamerchant.databinding.ActivityMarketListBinding

class MarketListActivity  : BaseActivity() {
    private lateinit var binding: ActivityMarketListBinding
    private lateinit var  viewModel: MarketListViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this).get(MarketListViewModel::class.java)
        viewModel.setViewModelListener(this)

        viewModel.handleFormLoad()
        viewModel.marketList?.observe( this ,
            Observer { t ->
                binding.root.layoutManager = LinearLayoutManager(this)

                var adaptor :MarketAdapter = MarketAdapter(t, { position, market ->   } )
                binding.root.adapter = adaptor
            }
        )

    }
}