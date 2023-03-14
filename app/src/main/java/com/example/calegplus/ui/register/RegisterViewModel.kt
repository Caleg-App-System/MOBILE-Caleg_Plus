package com.example.calegplus.ui.register

import android.app.Application
import androidx.lifecycle.*
import com.example.calegplus.DatastoreManager
import com.example.calegplus.data.api.RegisterRequest
import com.example.calegplus.data.api.response.AuthResponse
import com.example.calegplus.data.api.response.BaseResponse
import com.example.calegplus.data.api.service.UserApi
import com.example.calegplus.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val pref : DatastoreManager,
    private val client : UserApi,
    private val userRepo: UserRepository,
    application: Application
) : AndroidViewModel(application) {
    val registerResult: MutableLiveData<BaseResponse<AuthResponse>> = MutableLiveData()
    fun registerUser(username: String, email: String, pwd: String, role: String, address: String, name: String) {
        registerResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val registerRequest = RegisterRequest(
                    username = username,
                    password = pwd,
                    email = email,
                    role = role,
                    address = address,
                    name = name
                )
                val response = userRepo.registerUser(registerRequest = registerRequest)
                if (response?.code() == 201) {
                    registerResult.value = BaseResponse.Success(response.body())
                } else {
                    registerResult.value = BaseResponse.Error(response?.errorBody()?.string())
                }

            } catch (ex: Exception) {
                registerResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

}