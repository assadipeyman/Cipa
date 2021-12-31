package com.cipa.cipamerchant.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.adaptor.MarketAdapter
import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.base.BaseFragment
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.databinding.ActivityMarketListBinding
import com.cipa.cipamerchant.databinding.FragmentHomeBinding
import com.cipa.cipamerchant.ui.market.MarketListViewModel
import com.cipa.cipamerchant.ui.supplier.SupplierListActivity
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat
import androidx.recyclerview.widget.GridLayoutManager

class HomeFragment :  BaseFragment<HomeViewModel>(HomeViewModel::class.java) {

    private var binding:FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        initFragment()

        viewModel.handleFormLoad()
        viewModel.marketList?.observe(viewLifecycleOwner,
            Observer { t ->
                //binding!!.rvMarket.layoutManager = LinearLayoutManager(requireContext())
                binding!!.rvMarket.layoutManager  = GridLayoutManager(requireContext(), 2)

                var adaptor = MarketAdapter(t,
                    {
                            position, market ->
                        val intent = Intent(requireActivity(), SupplierListActivity::class.java)
                        val mBundle = Bundle()
                        mBundle.putInt("marketid", market.merchantBDM.id)
                        intent.putExtras(mBundle)
                        startActivity(intent)
                    })
                binding!!.rvMarket.adapter = adaptor
            }
        )
        viewModel.userData.observe(viewLifecycleOwner, Observer { t ->
            binding!!.tvTotalDebt.text =
                12500000.0.withCurrencyFormat  //t.sumRealDebt.toString()
            binding!!.tvMaxCredit.text =
                12500000000.0.withCurrencyFormat  //t.maxCredit.toString()
        })
        return  binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun OnAction(type: BaseViewModel.ActionType) {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleFormLoad()
    }
}