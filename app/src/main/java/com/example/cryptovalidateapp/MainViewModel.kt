package com.example.cryptovalidateapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class MainViewModel : ViewModel() {

    val cryptoLiveData = MutableLiveData<String>()
    val cryptoAddressLiveData = MutableLiveData<String>()

    val isAddressValid = MutableLiveData<Boolean>()

    fun updateIsAddressValid(isValid:Boolean){
        Timber.tag("Testing").d("updatingIsAddressValid with $isValid")
        isAddressValid.value = isValid
    }

    fun updateCryptoValue(crypto: String) {
        cryptoLiveData.value = crypto
    }

    fun updateCryptoAddressLiveData(address: String) {
        cryptoAddressLiveData.value = address
    }
}