package com.covidapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.covidapp.databinding.FragmentHomeBinding
import com.covidapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped

/**
 * @author emre.memis@ovidos.com
 */

@ActivityScoped
@AndroidEntryPoint
class HomeFragment : Fragment() {
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
        viewModel.loadingMutableLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: loading status $it")
        }
        viewModel.covidListMutableLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: $it")
        }
        viewModel.throwableMutableLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: something went error ${it.localizedMessage}")
        }
    }

    companion object {
        private const val TAG = "HomeFragment.kt"
    }
}