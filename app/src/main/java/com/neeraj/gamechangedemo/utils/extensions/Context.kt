package com.neeraj.gamechangedemo.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(textToToast:String) {
    Toast.makeText(this,textToToast, Toast.LENGTH_SHORT).show()
}

fun Context?.toast(@StringRes resourceID:Int) {
    Toast.makeText(this,resourceID, Toast.LENGTH_SHORT).show()
}
