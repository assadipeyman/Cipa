package com.cipa.cipamerchant.ui.credit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.base.BaseBottomSheetFragment
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.databinding.ActivitySupplierInfoBinding
import com.cipa.cipamerchant.databinding.FragmentCreditChargeBinding
import com.cipa.cipamerchant.ui.supplier.SupplierListActivity
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat
import com.cipa.cipamerchant.utils.StringUtils.withPersianDigits
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.DecimalFormat

class CreditChargeFragment :  BaseBottomSheetFragment<CreditChargeViewModel>(CreditChargeViewModel::class.java) {
    private lateinit  var binding: FragmentCreditChargeBinding
    lateinit var inflater: LayoutInflater
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        val view: View = inflater.inflate(R.layout.fragment_credit_charge, container, false)
        binding = FragmentCreditChargeBinding.bind(view)
        initFragment()
        viewModel.handleFormLoad(
            requireArguments().getInt("creditid"),
        )

        binding.tvName.text =requireArguments().getString("suppliername")

        viewModel.message.observe(this, androidx.lifecycle.Observer { t ->
            showMessage(t)
        })

        binding.btnCharge.setOnClickListener(View.OnClickListener { v ->
            viewModel.charge(Integer.parseInt(binding.etChargeAmount.text.toString().replace(",","")))
        })

        binding.etChargeAmount.addTextChangedListener(textWatcher)

        viewModel.state.observe(this , Observer { t ->
            if(t) {
                (activity as SupplierListActivity).updateUi()
                (dialog as BottomSheetDialog).dismiss()
            }
        })

/*        (dialog as? BottomSheetDialog)?.behavior?.apply {
            state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }*/
        return view
    }

private var flag: Int=0;

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if(flag==0) {
                if(!s.isNullOrEmpty()) {
                    flag = 1
                    val txt = DecimalFormat("#,###").format(
                        Integer.parseInt(
                            s.toString().replace(",", "")
                        )
                    ).withPersianDigits
                    binding.etChargeAmount.setText(txt)
                    binding.etChargeAmount.setSelection(binding.etChargeAmount.text!!.length)
                    flag = 0
                }
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
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
}