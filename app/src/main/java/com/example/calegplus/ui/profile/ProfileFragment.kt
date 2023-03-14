package com.example.calegplus.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calegplus.R
import com.example.calegplus.databinding.FragmentProfileBinding
import com.example.calegplus.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LoginViewModel>()
    private val profileViewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.getDataStoreId().observe(viewLifecycleOwner){
            profileViewModel.getUserProfile(it)
        }
        profileViewModel.user.observe(viewLifecycleOwner){
            binding.apply {
                if (it != null){
                    etUserName.text = it.data?.username.toString()
                    namaLengkap.text = it.data?.name.toString()
                    role.text = it.data?.role.toString()
                    areaKerja.text = it.data?.workingArea.toString()
                    etAddress.text = it.data?.address.toString()
                    etPhone.text = it.data?.phone?.toString()
                }
            }
        }


        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
        binding.btnLogout.setOnClickListener {
            viewModel.removeIsLoginStatus()
            viewModel.removeUsername()
            viewModel.getDataStoreIsLogin().observe(viewLifecycleOwner){
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}