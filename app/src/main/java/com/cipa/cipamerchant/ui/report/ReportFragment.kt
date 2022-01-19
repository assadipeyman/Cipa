package com.cipa.cipamerchant.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cipa.cipamerchant.adaptor.MarketAdapter
import com.cipa.cipamerchant.base.BaseFragment
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.databinding.FragmentHomeBinding
import com.cipa.cipamerchant.ui.supplier.SupplierListActivity
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cipa.cipamerchant.adaptor.ReportAdapter
import com.cipa.cipamerchant.databinding.FragmentReportsBinding

class ReportFragment :  BaseFragment<ReportViewModel>(ReportViewModel::class.java) {

    private var binding:FragmentReportsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportsBinding.inflate(layoutInflater)
        initFragment()

        viewModel.handleFormLoad()
        viewModel.reports?.observe(viewLifecycleOwner,
            Observer { t ->
                //binding!!.rvMarket.layoutManager = LinearLayoutManager(requireContext())
                binding!!.rvReport.layoutManager  = LinearLayoutManager(requireContext())

                var adaptor = ReportAdapter(t,
                    {
                            position, market ->
                    })
                binding!!.rvReport.adapter = adaptor
            }
        )
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
    }
}