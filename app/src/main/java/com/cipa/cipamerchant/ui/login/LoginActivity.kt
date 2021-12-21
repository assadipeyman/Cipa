package com.cipa.cipamerchant.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cipa.cipamerchant.R
import com.cipa.cipamerchant.databinding.ActivityLoginBinding
import com.cipa.cipamerchant.databinding.ActivityMainBinding
import com.cipa.cipamerchant.listener.ViewModelListener
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginActivity : AppCompatActivity(), ViewModelListener {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun showWaiting() {
        TODO("Not yet implemented")
    }

    override fun closeWaiting() {
        TODO("Not yet implemented")
    }
}