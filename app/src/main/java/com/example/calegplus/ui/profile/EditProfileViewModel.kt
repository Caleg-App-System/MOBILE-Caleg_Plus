package com.example.calegplus.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.calegplus.DatastoreManager
import com.example.calegplus.data.api.UpdateUserResponse
import com.example.calegplus.data.api.service.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val client: UserApi,
    private val pref: DatastoreManager,
    application: Application
) : AndroidViewModel(application) {

}