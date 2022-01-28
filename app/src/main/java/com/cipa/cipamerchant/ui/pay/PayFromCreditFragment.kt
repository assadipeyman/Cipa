package com.cipa.cipamerchant.ui.credit

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.base.BaseBottomSheetFragment
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.data.model.MessageDialogData
import com.cipa.cipamerchant.databinding.FragmentCreditChargeBinding
import com.cipa.cipamerchant.databinding.FragmentPayFromCreditBinding
import com.cipa.cipamerchant.ui.driver.DriverListActivity
import com.cipa.cipamerchant.ui.supplier.SupplierListActivity
import com.cipa.cipamerchant.utils.StringUtils.withCurrencyFormat
import com.cipa.cipamerchant.utils.StringUtils.withPersianDigits
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.DecimalFormat

class PayFromCreditFragment :  BaseBottomSheetFragment<PayFromCreditViewModel>(PayFromCreditViewModel::class.java) {
    private lateinit  var binding: FragmentPayFromCreditBinding
    lateinit var inflater: LayoutInflater
    var creditId:Int=0
    var supplierId:Int=0
    var marketId:Int=0
    var suppliername:Int=0
    var driverId:Int=0
    var driverName:String=""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        val view: View = inflater.inflate(R.layout.fragment_pay_from_credit, container, false)
        binding = FragmentPayFromCreditBinding.bind(view)
        initFragment()
        creditId =  requireArguments().getInt("creditid")
        supplierId =  requireArguments().getInt("supplierid")
        marketId =  requireArguments().getInt("marketid")
        viewModel.handleFormLoad(creditId)

        binding.tvName.text =requireArguments().getString("suppliername")

        viewModel.message.observe(this, androidx.lifecycle.Observer { t ->
            showMessage(t)
        })

        binding.tvDriver.setOnClickListener( View.OnClickListener {
            v ->
            val intent = Intent(requireActivity(), DriverListActivity::class.java)
            val mBundle = Bundle()
            mBundle.putInt("marketid", marketId)
            mBundle.putInt("supplierid", supplierId)
            mBundle.putInt("creditid", creditId)
            intent.putExtras(mBundle)
            startActivityForResult(intent , 1)
        })

        binding.btnCharge.setOnClickListener(View.OnClickListener { v ->
            try {
                viewModel.pay(
                    Integer.parseInt(binding.etPayAmount.text.toString().replace(",", "")),
                    binding.etInvoiceNumber.text.toString(),
                    driverId
                )
            }catch(ex:Exception){
                showMessage( MessageDialogData(
                    SweetAlertDialog.ERROR_TYPE,
                    "خرید",
                    "اطلاعات را به درستی وارد نمایید"
                )
                );
            }
        })

        binding.etPayAmount.addTextChangedListener(textWatcher)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            driverName = data!!.getStringExtra("drivername")!!
            driverId = data!!.getIntExtra("driverid", 0)
            binding.tvDriver.text = driverName
        }
    }

private var flag: Int=0;

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            try {
                if (flag == 0) {
                    if (!s.isNullOrEmpty()) {
                        flag = 1
                        val txt = DecimalFormat("#,###").format(
                            Integer.parseInt(
                                s.toString().replace(",", "")
                            )
                        ).withPersianDigits
                        binding.etPayAmount.setText(txt)
                        binding.etPayAmount.setSelection(binding.etPayAmount.text!!.length)
                        flag = 0
                    }
                }
            }catch (ex:Exception)
            {
                flag = 0
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