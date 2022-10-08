package com.example.cryptovalidateapp.ui.info

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cryptovalidateapp.MainViewModel
import com.example.cryptovalidateapp.R
import com.example.cryptovalidateapp.databinding.FragmentInformationBinding
import com.example.cryptovalidateapp.utils.showSnackBar
import com.example.cryptovalidateapp.utils.validateBTCAddress
import com.example.cryptovalidateapp.utils.validateETHAddress

class InformationFragment : Fragment() {

    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater)

        val cryptoName = mainViewModel.cryptoLiveData.value
        val cryptoAddress = mainViewModel.cryptoAddressLiveData.value ?: ""

        binding.textCoin.text = cryptoName
        binding.textAddress.text = cryptoAddress

        val validateButton = binding.buttonValidate
        validateButton.setOnClickListener {
            if (cryptoName == "btc") {
                mainViewModel.updateIsAddressValid(validateBTCAddress(cryptoAddress))
            } else if (cryptoName == "eth") {
                mainViewModel.updateIsAddressValid(validateETHAddress(cryptoAddress))
            }
        }

        val shareButton = binding.buttonShare
        val verifiedText = binding.textVerified
        mainViewModel.isAddressValid.observe(viewLifecycleOwner) {
            if (it != null) {
                shareButton.isEnabled = it
                if (it) {
                    validateButton.text = "Address Validated"
                    verifiedText.apply {
                        text = "Address Validated"
                        setTextColor(resources.getColor(R.color.verified))
                    }
                } else {
                    verifiedText.apply {
                        text = "Address Invalid"
                        setTextColor(resources.getColor(R.color.not_verified))
                    }
                }
                validateButton.isEnabled = false
            }
        }

        binding.buttonShare.setOnClickListener {
            if (shareButton.isEnabled) {
                val shareIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, cryptoAddress)
                    type = "text/plain"
                }
                val intent = Intent.createChooser(shareIntent, "Sharing BTC/ETH Address")
                startActivity(intent)
            } else {
                it.showSnackBar("Validate address first to share")
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}