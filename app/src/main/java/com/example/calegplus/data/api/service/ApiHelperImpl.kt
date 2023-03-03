package com.example.calegplus.data.api.service

import com.example.calegplus.data.api.LoginRequest
import com.example.calegplus.data.api.RegisterRequest
import com.example.calegplus.data.api.response.AuthResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: UserApi

): ApiHelper {
    override suspend fun loginUser(loginRequest: LoginRequest): Response<AuthResponse>? =
        apiService.loginUser(loginRequest = loginRequest)

    override suspend fun registerUser(registerRequest: RegisterRequest): Response<AuthResponse>? =
        apiService.registerUser(registerRequest = registerRequest)

}