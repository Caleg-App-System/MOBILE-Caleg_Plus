package com.example.calegplus

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.calegplus.data.api.GetUserResponse
import com.example.calegplus.data.api.LoginRequest
import com.example.calegplus.data.api.response.AuthResponse
import com.example.calegplus.data.api.response.BaseResponse
import com.example.calegplus.data.api.service.UserApi
import com.example.calegplus.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val client: UserApi,
    private val userRepo: UserRepository,
    application: Application
) : AndroidViewModel(application) {

    val loginResult: MutableLiveData<BaseResponse<AuthResponse>> = MutableLiveData()
    private val _user: MutableLiveData<GetUserResponse?> = MutableLiveData()
    val user: LiveData<GetUserResponse?> get() = _user

    fun auth(email: String, password: String) {
        client.getAllUsers()
            .enqueue(object : Callback<List<GetUserResponse>> {
                override fun onResponse(
                    call: Call<List<GetUserResponse>>,
                    response: Response<List<GetUserResponse>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            //Log.d(ContentValues.TAG, "onResponse: ${responseBody.toString()}")
                            for (i in responseBody.indices) {
                                if (responseBody[i].email.equals(
                                        email.toString(),
                                        ignoreCase = false
                                    ) && responseBody[i].password.equals(
                                        password.toString(), ignoreCase = false
                                    )
                                ) {
                                    _user.postValue(responseBody[i])
                                } else {
                                    _user.postValue(null)
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<GetUserResponse>>, t: Throwable) {
                }
            })
    }

//    fun loginUser(username: String, pwd: String) {
//
//        loginResult.value = BaseResponse.Loading()
//        viewModelScope.launch {
//            try {
//                val loginRequest = LoginRequest(
//                    password = pwd,
//                    username = username
//                )
//                val response = userRepo.loginUser(loginRequest = loginRequest)
//                if (response?.code() == 200) {
//                    loginResult.value = BaseResponse.Success(response.body())
//                } else {
//                    loginResult.value = BaseResponse.Error(response?.message())
//                }
//
//            } catch (ex: Exception) {
//                loginResult.value = BaseResponse.Error(ex.message)
//            }
//        }
//    }
}