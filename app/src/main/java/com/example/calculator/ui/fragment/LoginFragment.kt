package com.example.calculator.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calculator.R
import com.example.calculator.databinding.FragmentLoginBinding
import com.example.calculator.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    var number: String = ""
    var otp: String = ""
    val viewModel: MainViewModel by viewModels()
    private var _fragmentLoginBinding: FragmentLoginBinding? = null
    private val mBinding get() = _fragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mBinding?.tvEmail?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                number = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        mBinding?.tvPassword?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                otp = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        mBinding?.btnSubmit?.setOnClickListener {
            if (number == viewModel.loginNumber && otp == viewModel.otp) {
                findNavController().navigate(R.id.action_loginFragment_to_calculatorFragment)
            }else{
                Toast.makeText(context, "Please try same hint id and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        _fragmentLoginBinding = null
        super.onDestroy()
    }

}