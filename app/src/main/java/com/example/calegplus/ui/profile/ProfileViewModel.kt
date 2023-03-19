package com.example.calegplus.ui.profile

import android.app.Application
import androidx.lifecycle.*
import com.example.calegplus.DatastoreManager
import com.example.calegplus.data.api.UpdateUserResponse
import com.example.calegplus.data.api.response.GetUserResponse
import com.example.calegplus.data.api.service.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val client: UserApi,
    private val pref: DatastoreManager,
    application: Application
) : AndroidViewModel(application) {
    private val _user: MutableLiveData<GetUserResponse?> = MutableLiveData()
    val user: LiveData<GetUserResponse?> get() = _user

    fun getUserProfile(id: Int) {
        client.getUserProfile(id)
            .enqueue(object : Callback<GetUserResponse> {
                override fun onResponse(
                    call: Call<GetUserResponse>,
                    response: Response<GetUserResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _user.postValue(responseBody)
                        }
                    }
                }

                override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                }
            })
    }
    private val _update: MutableLiveData<UpdateUserResponse?> = MutableLiveData()
    val update: LiveData<UpdateUserResponse?> get() = _update


    fun updateUser(
        name: String,
        phone: String,
        address: String,
        token: String
    ){
        client.updateUser(UpdateUserResponse(name, phone, address), token)
            .enqueue(object : Callback<UpdateUserResponse> {
                override fun onResponse(
                    call: Call<UpdateUserResponse>,
                    response: Response<UpdateUserResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _update.postValue(responseBody)
                        }
                    }
                }

                override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                    _update.postValue(null)
                }
            })
    }

    fun saveName(name: String) {
        viewModelScope.launch {
            pref.saveName(name)
        }
    }
    fun savePhone(phone: String) {
        viewModelScope.launch {
            pref.savePhone(phone)
        }
    }
    fun saveAddress(address: String) {
        viewModelScope.launch {
            pref.saveAddress(address)
        }
    }
    fun getDataStoreName(): LiveData<String> {
        return pref.getName.asLiveData()
    }
    fun getDataStorePhone(): LiveData<String> {
        return pref.getPhone.asLiveData()
    }
    fun getDataStoreAddress(): LiveData<String> {
        return pref.getAddress.asLiveData()
    }

    fun getDataStoreToken(): LiveData<String> {
        return pref.getToken.asLiveData()
    }
    fun getDataStoreId(): LiveData<Int> {
        return pref.getId.asLiveData()
    }
}