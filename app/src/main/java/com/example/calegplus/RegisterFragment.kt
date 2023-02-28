package com.example.calegplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calegplus.databinding.FragmentLoginBinding
import com.example.calegplus.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRole()
        getAddress()

        binding.login.setOnClickListener(){
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnRegister.setOnClickListener {
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            val pwd = binding.password.text.toString()
            val confPwd = binding.cPassword.text.toString()

            if(username.isEmpty()){
                binding.username.error = "Username harus terisi"
            }
            if(email.isEmpty()){
                binding.email.error = "Email harus terisi"
            }
            if(pwd.isEmpty()){
                binding.password.error = "Password harus terisi"
            }
            if(confPwd.isEmpty()){
                binding.cPassword.error = "Confirm Password harus terisi"
            }
            if(pwd != confPwd){
                binding.cPassword.error = "Password harus sama"
            }
            if (pwd == confPwd){
                viewModel.insertUser(username, email, pwd)
                Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }


    }
    fun getRole(){
        val arrayRole = resources.getStringArray(R.array.role)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, arrayRole)
        binding.actvClass.setAdapter(arrayAdapter)
    }
    fun getAddress(){
        val arrayProv = resources.getStringArray(R.array.provinsi)
        val arrayAdapterProv = ArrayAdapter(requireContext(), R.layout.dropdown_item, arrayProv)
        binding.provClass.setAdapter(arrayAdapterProv)

        val arrayKab = resources.getStringArray(R.array.kabupaten)
        val arrayAdapterKab = ArrayAdapter(requireContext(), R.layout.dropdown_item, arrayKab)
        binding.kabClass.setAdapter(arrayAdapterKab)

        val arrayKec = resources.getStringArray(R.array.kecamatan)
        val arrayAdapterKec = ArrayAdapter(requireContext(), R.layout.dropdown_item, arrayKec)
        binding.kecClass.setAdapter(arrayAdapterKec)
    }

}