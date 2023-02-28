package com.example.calegplus.repository

import com.example.calegplus.data.api.LoginRequest
import com.example.calegplus.data.api.RegisterRequest
import com.example.calegplus.data.api.service.ApiHelper
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun loginUser(loginRequest: LoginRequest) =
        apiHelper.loginUser(loginRequest = loginRequest)

    suspend fun registerUser(registerRequest: RegisterRequest) =
        apiHelper.registerUser(registerRequest = registerRequest)
}