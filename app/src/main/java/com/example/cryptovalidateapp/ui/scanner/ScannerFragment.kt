package com.example.cryptovalidateapp.ui.scanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.example.cryptovalidateapp.R
import com.example.cryptovalidateapp.databinding.FragmentScannerBinding

class ScannerFragment : Fragment() {

    private lateinit var binding: FragmentScannerBinding

    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScannerBinding.inflate(inflater)

        scannerView = binding.scanner
        codeScanner = CodeScanner(requireActivity(), scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            requireActivity().runOnUiThread {
                findNavController().navigate(R.id.action_scannerFragment_to_informationFragment)
            }
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