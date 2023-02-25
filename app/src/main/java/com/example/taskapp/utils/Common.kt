package com.example.taskapp.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object Common {

    lateinit var globalContext: Context

    private var something: String = ""
}