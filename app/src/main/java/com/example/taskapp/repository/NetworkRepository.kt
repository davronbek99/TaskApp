package com.example.taskapp.repository

import com.example.taskapp.api.ApiService
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getList() = apiService.getList()
}