package com.cipa.cipamerchant.ui.login


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
import com.cipa.cipamerchant.base.BaseActivity

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var  loginViewModel:LoginViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.setViewModelListener(this)

        binding.txtUsername.setText("mcUsr1")
        binding.textPassword.setText("123456")
        binding.btnLogin.setOnClickListener { loginViewModel.handleLoginClick(binding.txtUsername.text.toString() , binding.textPassword.text.toString()) }
    }
}