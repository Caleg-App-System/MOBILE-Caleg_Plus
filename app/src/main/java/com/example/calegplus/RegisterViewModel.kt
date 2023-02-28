package com.example.calegplus

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.calegplus.data.api.PostUserResponse
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
    private val client : UserApi,
    private val userRepo: UserRepository,
    application: Application
) : AndroidViewModel(application) {
    val registerResult: MutableLiveData<BaseResponse<AuthResponse>> = MutableLiveData()
    val user: MutableLiveData<PostUserResponse?> = MutableLiveData()

    fun insertUser(username: String, email: String, password: String) {
        client.insertUser(
            RegisterRequest(username, email, password)
        )
            .enqueue(object : retrofit2.Callback<PostUserResponse> {
                override fun onResponse(
                    call: retrofit2.Call<PostUserResponse>,
                    response: retrofit2.Response<PostUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    } else {
                        Log.d("Error", response.message())
                        user.postValue(null)
                    }
                }

                override fun onFailure(call: retrofit2.Call<PostUserResponse>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                    user.postValue(null)
                }

            })
    }
//    fun registerUser(username: String, email: String, pwd: String) {
//        registerResult.value = BaseResponse.Loading()
//        viewModelScope.launch {
//            try {
//                val registerRequest = RegisterRequest(
//                    username = username,
//                    password = pwd,
//                    email = email
//                )
//                val response = userRepo.registerUser(registerRequest = registerRequest)
//                if (response?.code() == 201) {
//                    registerResult.value = BaseResponse.Success(response.body())
//                } else {
//                    registerResult.value = BaseResponse.Error(response?.message())
//                }
//
//            } catch (ex: Exception) {
//                registerResult.value = BaseResponse.Error(ex.message)
//            }
//        }
//    }
}