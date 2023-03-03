package com.example.calegplus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calegplus.databinding.FragmentHomeBinding
import com.example.calegplus.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDataStoreUsername().observe(viewLifecycleOwner){
            binding.txtHi.text = "Hi $it"
        }

        binding.logout.setOnClickListener{
            viewModel.removeUsername()
            viewModel.removeIsLoginStatus()
            viewModel.getDataStoreIsLogin().observe(viewLifecycleOwner){
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }
    }
}