package com.covidapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.covidapp.databinding.FragmentHomeBinding
import com.covidapp.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author emre.memis@ovidos.com
 */

@AndroidEntryPoint
class HomeFragment : Fragment(),
        TabLayout.OnTabSelectedListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.isDaily = true

        viewModel.loadingMutableLiveData.observe(viewLifecycleOwner) {
            binding.loading = it
        }
        viewModel.covidListMutableLiveData.observe(viewLifecycleOwner) {
            binding.covid = it
        }
        viewModel.throwableMutableLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(view, it.localizedMessage!!, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.fetch()

        binding.fragmentHomeTopContainerTabLayout
            .addOnTabSelectedListener(this@HomeFragment)

        binding.fragmentHomeTopContainerCallButtonView
                .setOnClickListener {
                    Intent(Intent.ACTION_DIAL)
                            .apply { data = Uri.parse("tel:112") }
                            .also { intent -> startActivity(intent) }
                }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        binding.isDaily = tab?.position == 0
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        viewModel.fetch()
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
}