package com.example.taskapp.responseApis

sealed class ResApis<T> {

    class Success<T>(val data: T) : ResApis<T>()

    class Error<T>(val message: String) : ResApis<T>()

    class Loading<T> : ResApis<T>()
}