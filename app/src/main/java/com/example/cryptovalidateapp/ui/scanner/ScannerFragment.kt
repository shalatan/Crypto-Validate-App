package com.example.cryptovalidateapp.ui.scanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.example.cryptovalidateapp.MainViewModel
import com.example.cryptovalidateapp.R
import com.example.cryptovalidateapp.databinding.FragmentScannerBinding
import com.example.cryptovalidateapp.utils.showSnackBar

class ScannerFragment : Fragment() {

    private var _binding: FragmentScannerBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScannerBinding.inflate(inflater)

        scannerView = binding.scanner
        codeScanner = CodeScanner(requireActivity(), scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            requireActivity().runOnUiThread {
                mainViewModel.updateCryptoAddressLiveData(it.text)
                findNavController().navigate(R.id.action_scannerFragment_to_informationFragment)
            }
        }
        codeScanner.errorCallback = ErrorCallback {
            requireActivity().runOnUiThread {
                binding.scanner.showSnackBar("Camera initialization error: ${it.message}")
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
        // clear previous validation result if we are coming from InformationFragment
        mainViewModel.clearAddressValidData()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}