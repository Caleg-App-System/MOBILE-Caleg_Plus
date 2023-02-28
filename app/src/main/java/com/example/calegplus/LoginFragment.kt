package com.example.calegplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calegplus.data.api.response.AuthResponse
import com.example.calegplus.data.api.response.BaseResponse
import com.example.calegplus.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.loginResult.observe(viewLifecycleOwner) {
//            when (it) {
//                is BaseResponse.Success -> {
//                    processLogin(it.data)
//                }
//                is BaseResponse.Error -> {
//                    processError(it.msg)
//                }
//                else -> {
//                }
//            }
//        }
        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val pwd = binding.password.text.toString()
            if(username.isEmpty()){
                binding.username.error = "Username harus terisi"
            }
            if(pwd.isEmpty()){
                binding.password.error = "Password harus terisi"
            }
            if(pwd.length < 8){
                binding.password.error = "Password minimal 8 karakter"
            }
            viewModel.auth(username, pwd)
            viewModel.user.observe(viewLifecycleOwner){
                if (it!= null) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    Toast.makeText(requireContext(), "Login Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
//    fun processLogin(data: AuthResponse?) {
//        showToast("Success:" + data?.message)
//            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//    }
//
//    fun processError(msg: String?) {
//        showToast("Error:" + msg)
//    }
//
//    fun showToast(msg: String) {
//        Toast.makeText(
//            requireContext(),
//            msg,
//            Toast.LENGTH_SHORT
//        ).show()
//    }
}