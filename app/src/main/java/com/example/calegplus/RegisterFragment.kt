package com.example.calegplus

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.calegplus.data.api.response.*
import com.example.calegplus.data.api.service.ApiClient
import com.example.calegplus.data.api.service.RetrofitClient
import com.example.calegplus.data.api.service.UserApi
import com.example.calegplus.databinding.FragmentLoginBinding
import com.example.calegplus.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

@AndroidEntryPoint
class RegisterFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegisterViewModel>()
    private var listIdProv = ArrayList<String>()
    private var listNameProv = ArrayList<String>()
    private var listIdKab = ArrayList<String>()
    private var listNameKab = ArrayList<String>()
    private var listIdKec = ArrayList<String>()
    private var listNameKec = ArrayList<String>()
    private var listIdDesa = ArrayList<String>()
    private var listNameDesa = ArrayList<String>()

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
        getProv()

        viewModel.registerResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Success -> {
                    processLogin(it.data)
                }
                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {}
            }
        }

        binding.login.setOnClickListener() {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnRegister.setOnClickListener {
            val username = binding.username.text.toString()
            val name = binding.namaLengkap.text.toString()
            val email = binding.email.text.toString()
            val pwd = binding.password.text.toString()
            val address =
                binding.desaClass.selectedItem.toString() + ", " + binding.kecClass.selectedItem.toString() + ", " + binding.kabClass.selectedItem.toString() + ", " + binding.provClass.selectedItem.toString()
            val role = binding.actvClass.text.toString()
            val confPwd = binding.cPassword.text.toString()

            if (username.isEmpty()) {
                binding.username.error = "Username harus terisi"
            }
            if (email.isEmpty()) {
                binding.email.error = "Email harus terisi"
            }
            if (pwd.isEmpty()) {
                binding.password.error = "Password harus terisi"
            }
            if (confPwd.isEmpty()) {
                binding.cPassword.error = "Confirm Password harus terisi"
            }
            if (pwd != confPwd) {
                binding.cPassword.error = "Password harus sama"
            }
            if (pwd == confPwd) {
                viewModel.registerUser(username, email, pwd, role, address, name)
            }
        }


    }

    fun getRole() {
        val arrayRole = resources.getStringArray(R.array.role)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, arrayRole)
        binding.actvClass.setAdapter(arrayAdapter)
    }

    fun getProv() {

        RetrofitClient.instance.getProv().enqueue(object :
            retrofit2.Callback<ProvinsiResponse> {
            override fun onResponse(
                call: Call<ProvinsiResponse>,
                response: Response<ProvinsiResponse>
            ) {
                val listResponse = response.body()?.data
                listResponse?.forEach() {
                    listIdProv.add(it.id)
                    listNameProv.add(it.name)
                }
                listIdProv.add(0, "0")
                listNameProv.add(0, "Alamat")
                binding.provClass.onItemSelectedListener = this@RegisterFragment

                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    listNameProv
                )
                binding?.provClass?.setAdapter(adapter)
                binding?.provClass?.setSelection(0)
            }

            override fun onFailure(call: Call<ProvinsiResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getKab(id: String) {
        RetrofitClient.instance.getKab(id).enqueue(object :
            retrofit2.Callback<KabupatenResponse> {
            override fun onResponse(
                call: Call<KabupatenResponse>,
                response: Response<KabupatenResponse>
            ) {
                val listResponse = response.body()?.data

                listIdKab.clear()
                listNameKab.clear()
                listResponse?.forEach() {
                    listIdKab.add(it.id)
                    listNameKab.add(it.name)
                }
                binding.kabClass.onItemSelectedListener = this@RegisterFragment
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    listNameKab
                )

                binding?.kabClass?.setAdapter(adapter)

            }

            override fun onFailure(call: Call<KabupatenResponse>, t: Throwable) {
            }

        })
    }

    fun getKec(id: String) {
        RetrofitClient.instance.getKec(id).enqueue(object :
            retrofit2.Callback<KecamatanResponse> {
            override fun onResponse(
                call: Call<KecamatanResponse>,
                response: Response<KecamatanResponse>
            ) {
                val listResponse = response.body()?.data

                listIdKec.clear()
                listNameKec.clear()
                listResponse?.forEach() {
                    listIdKec.add(it.id)
                    listNameKec.add(it.name)
                }
                binding.kecClass.onItemSelectedListener = this@RegisterFragment
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    listNameKec
                )

                binding?.kecClass?.setAdapter(adapter)
            }

            override fun onFailure(call: Call<KecamatanResponse>, t: Throwable) {
            }

        })
    }

    fun getDesa(id: String) {
        RetrofitClient.instance.getDesa(id).enqueue(object :
            retrofit2.Callback<DesaResponse> {
            override fun onResponse(
                call: Call<DesaResponse>,
                response: Response<DesaResponse>
            ) {
                val listResponse = response.body()?.data

                listIdDesa.clear()
                listNameDesa.clear()
                listResponse?.forEach() {
                    listIdDesa.add(it.id)
                    listNameDesa.add(it.name)
                }
                binding.desaClass.onItemSelectedListener = this@RegisterFragment
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    listNameDesa
                )

                binding?.desaClass?.setAdapter(adapter)
            }

            override fun onFailure(call: Call<DesaResponse>, t: Throwable) {
            }

        })
    }

    fun processLogin(data: AuthResponse?) {
        showToast("Success:" + data?.message)
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(
            requireContext(),
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
        when (parent?.selectedItem) {
            binding?.provClass?.selectedItem -> {
                getKab(listIdProv[position])
            }
            binding?.kabClass?.selectedItem -> {
                getKec(listIdKab[position])
            }
            binding?.kecClass?.selectedItem -> {
                getDesa(listIdKec[position])
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}