package com.example.taskapp.api

import com.example.taskapp.models.GetPhone
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("offers")
    suspend fun getList(): Response<GetPhone>
}