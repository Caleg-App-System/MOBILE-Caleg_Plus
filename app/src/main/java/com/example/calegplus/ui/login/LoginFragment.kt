package com.example.calegplus.ui.login

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calegplus.R
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
        viewModel.loginResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Success -> {
                    processLogin(it.data)
                }
                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                }
            }
        }
        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val pwd = binding.password.text.toString()
            if (username.isEmpty()) {
                binding.username.error = "Username harus terisi"
            }
            if (pwd.isEmpty()) {
                binding.password.error = "Password harus terisi"
            }
            if (pwd.length < 8) {
                binding.password.error = "Password minimal 8 karakter"
            }
            viewModel.loginUser(username = username, pwd = pwd)
        }
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getDataStoreIsLogin().observe(viewLifecycleOwner) {
            if (it == true) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

    fun processLogin(data: AuthResponse?) {
        showToast("Success:" + data?.message)
        viewModel.saveIsLoginStatus(true)
        viewModel.saveUsername(data?.data?.user?.username.toString())
        viewModel.saveId(data?.data?.user?.id.toString().toInt())
        viewModel.saveToken(data?.token.toString())
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    fun processError(msg: String?) {
        showToast("$msg")
    }

    fun showToast(msg: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("$msg")

        builder.setPositiveButton("OK") { dialog, which ->
            Toast.makeText(requireContext(),
                android.R.string.yes, Toast.LENGTH_SHORT).show()

        }
        val dialog = builder.create()
        dialog.show()
    }
}