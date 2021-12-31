package com.cipa.cipamerchant.ui.supplier

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.base.BaseViewModel
import android.view.View
import androidx.core.content.ContextCompat
import com.cipa.cipamerchant.MainActivity
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.databinding.ActivitySupplierInfoBinding
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat
import com.cipa.cipamerchant.utils.StringUtils.withPersianDigits

class SupplierInfoActivity  : BaseActivity<SupplierInfoViewModel>(SupplierInfoViewModel::class.java) {
    private lateinit var binding: ActivitySupplierInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitActivity()
        viewModel.handleFormLoad(intent.extras!!.getInt("marketid"),intent.extras!!.getInt("supplierid"))
        viewModel.supplier.observe(this, Observer<BSupplier?> { t ->
            binding.tvName.text = t.name
            binding.tvBalanceAmount.text = t.credit!!.balanceAmount.withCurrencyFormat
            binding.tvCreditAmount.text = t.credit!!.creditAmount.withCurrencyFormat

            val debt = (-1 * (t.credit!!.debt))
            binding.tvDebt.text = debt.withCurrencyFormat
            if(debt<0.0)
                    binding.tvDebt.setTextColor(ContextCompat.getColor(this,R.color.red_price))
                else
                    binding.tvDebt.setTextColor(ContextCompat.getColor(this,R.color.green_price))

            binding.tvDuration.text = t.credit!!.duration.withPersianDigits
            binding.tvGiftRate.text = (t.credit!!.giftRate * 100).withPersianDigits
            binding.tvInitPayable.text = t.credit!!.initPayable.withCurrencyFormat
            binding.tvNonPenaltyDuration.text = t.credit!!.noPenaltyDuration.withPersianDigits
            binding.tvPenaltyRate.text = (t.credit!!.penaltyRate * 100).withPersianDigits
        })
        viewModel.message.observe(this , Observer {
            t -> showMessage(t)
        })
        binding.btnCharge.setOnClickListener(View.OnClickListener { v ->
           // viewModel.charge(1200)
        })
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

    override fun onResume() {
        super.onResume()

    }
}