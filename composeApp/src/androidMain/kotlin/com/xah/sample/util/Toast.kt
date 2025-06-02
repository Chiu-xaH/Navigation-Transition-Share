package com.xah.sample.util

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.xah.sample.app.MyApplication

fun showToast(text : String) {
    Handler(Looper.getMainLooper()).post{ Toast.makeText(MyApplication.context,text,Toast.LENGTH_SHORT).show() }
}