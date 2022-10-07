package com.example.cryptovalidateapp.ui.scanner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.example.cryptovalidateapp.MainViewModel
import com.example.cryptovalidateapp.R
import com.example.cryptovalidateapp.databinding.FragmentScannerBinding

class ScannerFragment : Fragment() {

    private lateinit var binding: FragmentScannerBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScannerBinding.inflate(inflater)

        Log.e("Testing SF", mainViewModel.cryptoLiveData.value.toString())
        Log.e("Testing SF", mainViewModel.cryptoAddressLiveData.value.toString())

        scannerView = binding.scanner
        codeScanner = CodeScanner(requireActivity(), scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            requireActivity().runOnUiThread {
                mainViewModel.updateCryptoAddressLiveData(it.text)
            }
        }

        mainViewModel.cryptoAddressLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_scannerFragment_to_informationFragment)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }
}