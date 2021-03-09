package com.covidapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.covidapp.R
import com.covidapp.databinding.ActivityMainBinding
import com.covidapp.ext.Extensions.runOnHandler
import com.google.android.material.snackbar.Snackbar

/**
 * @author emre.memis@ovidos.com
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var appExitStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.fragment)
    }

    override fun onBackPressed() {
        when (navController.navigateUp()) {
            true -> super.onBackPressed()
            false -> prepareExit()
        }
    }

    private fun prepareExit() {
        when (appExitStatus) {
            true -> finishAffinity()
            else -> appExitStatus = true
        }
        Snackbar.make(
            binding.root,
            getString(R.string.app_exit_status_message),
            Snackbar.LENGTH_SHORT
        ).show()
        runOnHandler(APP_EXIT_MILLS_TIME) {
            appExitStatus = false
        }
    }

    companion object {
        private const val APP_EXIT_MILLS_TIME = 1500L
    }
}