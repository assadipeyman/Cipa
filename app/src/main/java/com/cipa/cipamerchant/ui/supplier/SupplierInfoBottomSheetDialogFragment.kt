package com.cipa.cipamerchant.ui.supplier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.base.BaseBottomSheetFragment
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.businessData.BSupplier
import com.cipa.cipamerchant.databinding.ActivitySupplierInfoBinding
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat
import com.cipa.cipamerchant.utils.StringUtils.withPersianDigits
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class SupplierInfoBottomSheetDialogFragment :  BaseBottomSheetFragment<SupplierInfoViewModel>(SupplierInfoViewModel::class.java) {
    private lateinit  var binding: ActivitySupplierInfoBinding
    lateinit var inflater: LayoutInflater
    lateinit var supplier: BSupplier
    lateinit var  thisDialog : BottomSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        val view :View =  inflater.inflate(R.layout.activity_supplier_info, container, false)
        binding = ActivitySupplierInfoBinding.bind(view)
        initFragment()
        viewModel.handleFormLoad(requireArguments().getInt("marketid"),requireArguments().getInt("supplierid"))
        viewModel.supplier.observe(this, androidx.lifecycle.Observer {
            t ->
            supplier = t
            binding.tvName.text = t.name
            binding.tvBalanceAmount.text = t.credit!!.balanceAmount.withCurrencyFormat
            binding.tvCreditAmount.text = t.credit!!.creditAmount.withCurrencyFormat

            val debt = (-1 * (t.credit!!.debt))
            binding.tvDebt.text = debt.withCurrencyFormat
            if(debt<0.0)
                binding.tvDebt.setTextColor(ContextCompat.getColor(requireContext(),R.color.red_price))
            else
                binding.tvDebt.setTextColor(ContextCompat.getColor(requireContext(),R.color.green_price))

            binding.tvDuration.text = t.credit!!.duration.withPersianDigits
            binding.tvGiftRate.text = (t.credit!!.giftRate * 100).withPersianDigits
            binding.tvInitPayable.text = t.credit!!.initPayable.withCurrencyFormat
            binding.tvNonPenaltyDuration.text = t.credit!!.noPenaltyDuration.withPersianDigits
            binding.tvPenaltyRate.text = (t.credit!!.penaltyRate * 100).withPersianDigits
        })
        viewModel.message.observe(this , androidx.lifecycle.Observer {
                t -> showMessage(t)
        })
        binding.btnCharge.setOnClickListener(View.OnClickListener { v ->
            thisDialog.dismiss()
            (requireActivity() as SupplierListActivity).openCharge(supplier)
        })
        binding.btnPay.setOnClickListener(View.OnClickListener { v ->
            thisDialog.dismiss()
            (requireActivity() as SupplierListActivity).openPay(supplier)
        })

        thisDialog =  dialog as BottomSheetDialog

        thisDialog?.behavior?.apply {
            state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun OnAction(type: BaseViewModel.ActionType) {
        when (type) {
            BaseViewModel.ActionType.SHOW_WAIT -> showWaiting()
            BaseViewModel.ActionType.CLOSE_WAIT -> closeWaiting()
            BaseViewModel.ActionType.SHOW_MARKET_ACTIVITY -> {
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