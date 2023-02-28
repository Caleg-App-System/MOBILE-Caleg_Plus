package com.example.calegplus.data.api.service

import com.example.calegplus.data.api.LoginRequest
import com.example.calegplus.data.api.RegisterRequest
import com.example.calegplus.data.api.response.AuthResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun loginUser(loginRequest: LoginRequest): Response<AuthResponse>?

    suspend fun registerUser(registerRequest: RegisterRequest): Response<AuthResponse>?
}