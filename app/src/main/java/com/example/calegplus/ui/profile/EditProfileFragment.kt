package com.example.calegplus.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calegplus.R
import com.example.calegplus.data.api.response.DesaResponse
import com.example.calegplus.data.api.response.KabupatenResponse
import com.example.calegplus.data.api.response.KecamatanResponse
import com.example.calegplus.data.api.response.ProvinsiResponse
import com.example.calegplus.data.api.service.RetrofitClient
import com.example.calegplus.databinding.FragmentEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Response

@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel by viewModels<ProfileViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUpdate.setOnClickListener {

            val name = binding.etUserName.text.toString().toRequestBody("multipart/form-data".toMediaType())
            val phone = binding.etPhone.text.toString().toRequestBody("multipart/form-data".toMediaType())
            val address = binding.etAddress.text.toString().toUpperCase().toRequestBody("multipart/form-data".toMediaType())
            profileViewModel.getDataStoreToken().observe(viewLifecycleOwner){
                profileViewModel.updateUser(name, phone, address, "Bearer $it")
            }
            Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
        }
        profileViewModel.getDataStoreName().observe(viewLifecycleOwner){
            binding.etUserName.setText(it)
        }
        profileViewModel.getDataStorePhone().observe(viewLifecycleOwner){
            binding.etPhone.setText(it)
        }
        profileViewModel.getDataStoreAddress().observe(viewLifecycleOwner){
            binding.etAddress.setText(it)
        }
    }
}