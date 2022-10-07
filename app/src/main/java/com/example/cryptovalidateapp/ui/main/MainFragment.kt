package com.example.cryptovalidateapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptovalidateapp.MainViewModel
import com.example.cryptovalidateapp.R
import com.example.cryptovalidateapp.databinding.FragmentMainBinding
import timber.log.Timber

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        binding.buttonBtc.setOnClickListener {
            mainViewModel.updateCryptoValue("btc")
            findNavController().navigate(R.id.action_mainFragment_to_scannerFragment)
        }

        binding.buttonEth.setOnClickListener {
            mainViewModel.updateCryptoValue("eth")
            findNavController().navigate(R.id.action_mainFragment_to_scannerFragment)
        }
        return binding.root
    }
}