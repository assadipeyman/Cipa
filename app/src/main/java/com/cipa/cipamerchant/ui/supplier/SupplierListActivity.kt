package com.cipa.cipamerchant.ui.supplier

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cipa.cipamerchant.adaptor.MarketAdapter
import com.cipa.cipamerchant.adaptor.SupplierAdapter
import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.databinding.ActivityMarketListBinding
import com.cipa.cipamerchant.utils.StringUtils
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.databinding.ActivitySupplierListBinding
import kotlinx.coroutines.newSingleThreadContext


class SupplierListActivity  : BaseActivity<SupplierListViewModel>(SupplierListViewModel::class.java),
    SearchView.OnQueryTextListener {
    private lateinit var binding: ActivitySupplierListBinding
    private  var adaptor : SupplierAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitViewModel()
        viewModel.handleFormLoad(intent.extras!!.getInt("marketid"))
        viewModel.supplierList?.observe(this,
            Observer { t ->
                binding.rvSupplier.layoutManager = LinearLayoutManager(this)

                adaptor = SupplierAdapter(t,                     {
                        position, suppler ->
                    val intent = Intent(this, SupplierInfoActivity::class.java)
                    val mBundle = Bundle()
                    mBundle.putInt("supplierid", suppler.id)
                    intent.putExtras(mBundle)
                    startActivity(intent)
                })
                binding.rvSupplier.adapter = adaptor
            }
        )
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun OnAction(type: BaseViewModel.ActionType) {
        TODO("Not yet implemented")
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adaptor!!.filter.filter(newText)
        return true;
    }
}