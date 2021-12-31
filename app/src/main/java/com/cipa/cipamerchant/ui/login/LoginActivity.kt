package com.cipa.cipamerchant.ui.login


import android.content.Intent
import android.os.Bundle
import com.cipa.cipamerchant.MainActivity
import com.cipa.cipamerchant.databinding.ActivityLoginBinding

import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.ui.market.MarketListActivity


class LoginActivity : BaseActivity<LoginViewModel>(LoginViewModel::class.java) {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitActivity()

        binding.txtUsername.setText("merchant")
        binding.textPassword.setText("123456")
        binding.btnLogin.setOnClickListener {
            viewModel.handleLoginClick(
                binding.txtUsername.text.toString(),
                binding.textPassword.text.toString()
            )
        }
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
}