package com.example.calegplus.ui.login

import android.app.Application
import androidx.lifecycle.*
import com.example.calegplus.DatastoreManager
import com.example.calegplus.data.api.LoginRequest
import com.example.calegplus.data.api.response.AuthResponse
import com.example.calegplus.data.api.response.BaseResponse
import com.example.calegplus.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val pref: DatastoreManager,
    private val userRepo: UserRepository,
    application: Application
) : AndroidViewModel(application) {

    val loginResult: MutableLiveData<BaseResponse<AuthResponse>> = MutableLiveData()

    fun loginUser(username: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = pwd,
                    username = username
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.errorBody()?.string())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
    fun saveIsLoginStatus(status: Boolean) {
        viewModelScope.launch {
            pref.saveIsLoginStatus(status)
        }
    }
    fun getDataStoreIsLogin(): LiveData<Boolean> {
        return pref.getIsLogin.asLiveData()
    }
    fun saveUsername(username: String) {
        viewModelScope.launch {
            pref.saveUsername(username)
        }
    }
    fun saveToken(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }
    fun saveId(id: Int) {
        viewModelScope.launch {
            pref.saveId(id)
        }
    }
    fun getDataStoreUsername(): LiveData<String> {
        return pref.getUsername.asLiveData()
    }

    fun removeIsLoginStatus() {
        viewModelScope.launch {
            pref.removeIsLoginStatus()
        }
    }
    fun removeUsername() {
        viewModelScope.launch {
            pref.removeUsername()
        }
    }
    fun removeToken() {
        viewModelScope.launch {
            pref.removeToken()
        }
    }
    fun removeId() {
        viewModelScope.launch {
            pref.removeId()
        }
    }
}