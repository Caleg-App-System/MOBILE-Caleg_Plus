package com.example.calegplus.data.api.service

import com.example.calegplus.data.api.GetUserResponse
import com.example.calegplus.data.api.LoginRequest
import com.example.calegplus.data.api.PostUserResponse
import com.example.calegplus.data.api.RegisterRequest
import com.example.calegplus.data.api.response.AuthResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @POST("/auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<AuthResponse>

    @POST("/auth/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<AuthResponse>

    @GET("auth")
    fun getAllUsers(): Call<List<GetUserResponse>>

    @POST("auth")
    fun insertUser(@Body request: RegisterRequest): Call<PostUserResponse>
}
