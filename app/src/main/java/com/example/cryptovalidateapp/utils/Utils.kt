package com.example.cryptovalidateapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.showSnackBarWithAction(
    message: String,
    length: Int,
    actionMessage: String,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(this,message,length)
    snackbar.setAction(actionMessage){
        action(this)
    }.show()
}

fun validateBTCAddress(address: String): Boolean {
    Timber.tag("Testing").e("validatingBtcAddress")
    if (address.length !in 26..35) return false
    if (address[0].toString() != "1" ||
        address.contains("0") ||
        address.contains("O") ||
        address.contains("l") ||
        address.contains("I")
    ) {
        return false
    }
    return true
}

fun validateETHAddress(address: String): Boolean {
    Timber.tag("Testing").e("validatingEthAddress")
    if (address[0] == '0' && address[1] == 'x') {
        val rest = address.subSequence(2, address.length)
        for (char in rest) {
            val charCode = char.toInt()
            if (charCode in 48..58 || charCode in 97..102 || charCode in 65..70) {
                continue
            } else {
                return false
            }
        }
    }
    return true
}