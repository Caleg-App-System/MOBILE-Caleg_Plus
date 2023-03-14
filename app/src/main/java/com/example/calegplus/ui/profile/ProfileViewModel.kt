package com.example.calegplus.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.calegplus.DatastoreManager
import com.example.calegplus.data.api.response.GetUserResponse
import com.example.calegplus.data.api.service.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
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
    fun getDataStoreId(): LiveData<Int> {
        return pref.getId.asLiveData()
    }
}