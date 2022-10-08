package com.example.cryptovalidateapp.ui.main

import android.Manifest
import android.content.pm.PackageManager
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
import com.example.cryptovalidateapp.utils.*
import com.google.android.material.snackbar.Snackbar

const val PERMISSION_CAMERA_REQUEST = 1234

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var layout: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        layout = binding.root

        binding.buttonBtc.setOnClickListener {
            mainViewModel.updateCryptoValue("btc")
            startScannerFragment()
        }

        binding.buttonEth.setOnClickListener {
            mainViewModel.updateCryptoValue("eth")
            startScannerFragment()
        }

        return binding.root
    }

    private fun navigate(){
        findNavController().navigate(R.id.action_mainFragment_to_scannerFragment)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CAMERA_REQUEST) {
            // Request for camera permission.
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                layout.showSnackBar("Camera Permission Granted")
                navigate()
            } else {
                // Permission request was denied.
                layout.showSnackBar("Camera Permission Denied")
            }
        }
    }

    /**
     * checks if permission is granted, if granted navigates to scannerFragment,
     * else request the permission
     */
    private fun startScannerFragment() {
        // Check if permission is granted
        if (requireActivity().checkSelfPermissionCompat(Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is available
            navigate()
        } else {
            // Permission is missing
            requestCameraPermission()
        }
    }

    /**
     * Request the user for the permission, if required show Rationale message
     */
    private fun requestCameraPermission() {
        // Permission has not been granted
        if (requireActivity().shouldShowRequestPermissionRationaleCompat(Manifest.permission.CAMERA)) {
            // Show a snackbar requesting for permission
            layout.showSnackBarWithAction(
                "Please give Camera permission to scan code",
                Snackbar.LENGTH_INDEFINITE, "OK"
            ) {
                requireActivity().requestPermissionsCompat(
                    arrayOf(Manifest.permission.CAMERA),
                    PERMISSION_CAMERA_REQUEST
                )
            }
        } else {
            layout.showSnackBar("Camera Permission not granted")
            // Request the permission
            requireActivity().requestPermissionsCompat(
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_CAMERA_REQUEST
            )
        }
    }
}