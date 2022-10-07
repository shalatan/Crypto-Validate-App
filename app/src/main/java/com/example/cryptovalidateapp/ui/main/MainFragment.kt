package com.example.cryptovalidateapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cryptovalidateapp.R
import com.example.cryptovalidateapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        binding.buttonBtc.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_scannerFragment)
        }

        binding.buttonEth.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_scannerFragment)
        }
        return binding.root
    }
}