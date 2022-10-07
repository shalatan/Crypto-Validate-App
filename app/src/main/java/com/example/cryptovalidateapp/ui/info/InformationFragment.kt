package com.example.cryptovalidateapp.ui.info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cryptovalidateapp.MainViewModel
import com.example.cryptovalidateapp.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater)

        Log.e("Testing IF", mainViewModel.cryptoLiveData.value.toString())
        Log.e("Testing IF", mainViewModel.cryptoAddressLiveData.value.toString())

        return binding.root
    }
}