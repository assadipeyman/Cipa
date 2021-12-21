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
import com.cipa.cipamerchant.databinding.ActivityMainBinding
import com.cipa.cipamerchant.listener.ViewModelListener
import com.cipa.cipamerchant.ui.home.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AlertDialog
import com.cipa.cipamerchant.R
import android.view.Gravity
import android.view.Window

import android.view.WindowManager





class LoginActivity : AppCompatActivity(), ViewModelListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var  loginViewModel:LoginViewModel;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.setViewModelListener(this)

        binding.txtUsername.setText("asdfas")
        binding.btnLogin.setOnClickListener { loginViewModel.handleLoginClick() }
    }
    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }

    lateinit var  dialog:AlertDialog;

    override fun showWaiting() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setCancelable(false) // if you want user to wait for some process to finish,

        builder.setView(R.layout.layout_progress)
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show();
    }

    override fun closeWaiting() {
        dialog.dismiss();
    }

}