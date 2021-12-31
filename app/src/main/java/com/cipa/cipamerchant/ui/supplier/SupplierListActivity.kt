package com.cipa.cipamerchant.ui.supplier

import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cipa.cipamerchant.adaptor.SupplierAdapter
import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.base.BaseViewModel
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.databinding.ActivitySupplierListBinding
import com.cipa.cipamerchant.ui.credit.CreditChargeFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class SupplierListActivity  : BaseActivity<SupplierListViewModel>(SupplierListViewModel::class.java),
    SearchView.OnQueryTextListener {
    private lateinit var binding: ActivitySupplierListBinding
    private  var adaptor : SupplierAdapter? = null
    private var marketId : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        InitActivity()
        marketId =  intent.extras!!.getInt("marketid")
        viewModel.handleFormLoad(marketId)
        viewModel.supplierList?.observe(this,
            Observer { t ->
                binding.rvSupplier.layoutManager = LinearLayoutManager(this)
                adaptor = SupplierAdapter(t,
                    { position, suppler ->
/*                        val intent = Intent(this, SupplierInfoActivity::class.java)
                        val mBundle = Bundle()
                        mBundle.putInt("supplierid", suppler.id)
                        mBundle.putInt("marketid", marketId)
                        intent.putExtras(mBundle)
                        startActivity(intent)*/
                        val bottomSheet : SupplierInfoBottomSheetDialogFragment = SupplierInfoBottomSheetDialogFragment()
                        val mBundle = Bundle()
                        mBundle.putInt("supplierid", suppler.id)
                        mBundle.putInt("marketid", marketId)
                        bottomSheet.arguments = mBundle
                        bottomSheet.show(getSupportFragmentManager(), "showinfg")

                    })
                binding.rvSupplier.adapter = adaptor
            }
        )
        viewModel.title.observe(this , Observer {
            t ->
            title = t
        })
    }

    fun updateUi(){
        viewModel.handleFormLoad(marketId)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }
     fun openCharge(supplier: BSupplier){
        val bottomSheet : CreditChargeFragment = CreditChargeFragment()
        val mBundle = Bundle()
        mBundle.putInt("creditid", supplier.id)
        mBundle.putString("suppliername", supplier.name)
        bottomSheet.arguments = mBundle
        bottomSheet.show(supportFragmentManager, "showinfg")
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

    override fun onResume() {
        super.onResume()}
}