package com.cipa.cipamerchant.ui.login


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cipa.cipamerchant.databinding.ActivityLoginBinding

import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.cipa.cipamerchant.adaptor.MarketAdapter
import com.cipa.cipamerchant.base.BaseActivity
import com.cipa.cipamerchant.base.BaseViewModel
import com.cipa.cipamerchant.ui.Market.MarketListActivity

class LoginActivity : BaseActivity<LoginViewModel>(LoginViewModel::class.java) {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitViewModel()

        binding.txtUsername.setText("mcUsr1")
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
                val intent = Intent(this, MarketListActivity::class.java).apply {
                }
                startActivity(intent)
            }
            else -> {
                print("x is neither 1 nor 2")
            }
        }
    }
}