package com.example.calegplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.calegplus.databinding.FragmentRegister3Binding
import com.example.calegplus.ui.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Register2Fragment : Fragment() {
    private var _binding: FragmentRegister3Binding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegister3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val username = arguments?.getString("username")
            val email = arguments?.getString("email")
            val pwd = arguments?.getString("pwd")
            val role = arguments?.getString("role")
            val address = arguments?.getString("address")
            val name = arguments?.getString("name")

            viewModel.registerUser(username, email, pwd, role, address, name)
        }
    }


}