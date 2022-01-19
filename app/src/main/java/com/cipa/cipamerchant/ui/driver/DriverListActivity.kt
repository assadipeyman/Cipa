package com.cipa.cipamerchant.ui.driver

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.base.BaseViewModel
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.cipa.cipamerchant.MainActivity
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.adaptor.DriverAdapter
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.databinding.ActivitySupplierListBinding
import com.cipa.cipamerchant.ui.credit.PayFromCreditFragment

class DriverListActivity  : BaseActivity<DriverListViewModel>(DriverListViewModel::class.java),
    SearchView.OnQueryTextListener {
    private lateinit var binding: ActivitySupplierListBinding
    private  var adaptor : DriverAdapter? = null
    private var marketId : Int = 0
    private var creditId : Int = 0
    private var supplierId : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        InitActivity()
        creditId =  intent.extras!!.getInt("creditid")
        supplierId =  intent.extras!!.getInt("supplierid")
        marketId =  intent.extras!!.getInt("marketid")
        viewModel.handleFormLoad(marketId , supplierId)
        viewModel.driverList?.observe(this,
            Observer { t ->
                binding.rvSupplier.layoutManager = LinearLayoutManager(this)
                adaptor = DriverAdapter(t , { position, driver ->
                    var data : Intent = Intent();
                    data.putExtra("driverid" , driver.Id)
                    data.putExtra("drivername" , driver.Name)
                    setResult(RESULT_OK, data);
                    finish();
                })
                binding.rvSupplier.adapter = adaptor
            }
        )
        viewModel.title.observe(this , Observer {
            t ->
            title = t
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun OnAction(type: BaseViewModel.ActionType) {
        when (type) {
            BaseViewModel.ActionType.SHOW_WAIT -> showWaiting()
            BaseViewModel.ActionType.CLOSE_WAIT -> closeWaiting()
            BaseViewModel.ActionType.SHOW_MARKET_ACTIVITY -> {
                val intent = Intent(this, MainActivity::class.java).apply {
                }
                startActivity(intent)
            }
            else -> {
                print("x is neither 1 nor 2")
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adaptor!!.filter.filter(newText)
        return true;
    }

    override fun onResume() {
        super.onResume()}
}