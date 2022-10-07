package com.example.cryptovalidateapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val cryptoLiveData = MutableLiveData<String?>()
    val cryptoAddressLiveData = MutableLiveData<String?>()

    fun updateCryptoValue(crypto: String) {
        cryptoLiveData.value = crypto
    }

    fun updateCryptoAddressLiveData(address: String) {
        cryptoAddressLiveData.value = address
    }

    fun clearData() {
        cryptoLiveData.value = null
        cryptoAddressLiveData.value = null
    }

    // TODO: Implement the ViewModel
}