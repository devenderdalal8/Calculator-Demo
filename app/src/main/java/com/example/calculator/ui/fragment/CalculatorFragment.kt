package com.example.calculator.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calculator.R
import com.example.calculator.data.db.DataEntity
import com.example.calculator.databinding.FragmentCalculatorBinding
import com.example.calculator.ui.MainActivity
import com.example.calculator.ui.MainViewModel
import com.example.calculator.util.Extension
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorFragment : Fragment() {
    lateinit var viewModel: MainViewModel
    var screenNumber = ""
    lateinit var calculatorFragment: FragmentCalculatorBinding
    private val mBinding get() = calculatorFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        calculatorFragment = FragmentCalculatorBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        mBinding.buttonClear.setOnClickListener {
            updateNumber("", true)
        }
        mBinding.buttonAddition.setOnClickListener {
            updateNumber("+", false)
        }
        mBinding.buttonDivision.setOnClickListener {
            updateNumber("/", false)
        }
        mBinding.buttonMultiplication.setOnClickListener {
            updateNumber("*", false)
        }
        mBinding.buttonSubtraction.setOnClickListener {
            updateNumber("-", false)
        }
        mBinding.buttonEqual.setOnClickListener {
            equal()
        }
        mBinding.buttonBackspace.setOnClickListener {
            backSpace()
        }
        mBinding.buttonHistory.setOnClickListener {
            findNavController().navigate(R.id.action_calculatorFragment_to_historyFragment)
        }
        mBinding.buttonZero.setOnClickListener {
            updateNumber("0", false)
        }
        mBinding.buttonOne.setOnClickListener {
            updateNumber("1", false)
        }
        mBinding.buttonTwo.setOnClickListener {
            updateNumber("2", false)
        }
        mBinding.buttonThree.setOnClickListener {
            updateNumber("3", false)
        }
        mBinding.buttonFour.setOnClickListener {
            updateNumber("4", false)
        }
        mBinding.buttonFive.setOnClickListener {
            updateNumber("5", false)
        }
        mBinding.buttonSix.setOnClickListener {
            updateNumber("6", false)
        }
        mBinding.buttonSeven.setOnClickListener {
            updateNumber("7", false)
        }
        mBinding.buttonEight.setOnClickListener {
            updateNumber("8", false)
        }
        mBinding.buttonNine.setOnClickListener {
            updateNumber("9", false)
        }
    }

    private fun equal() {
        if (screenNumber.isNotEmpty()) {
            if (screenNumber.last().isDigit()) {
                setupResult(screenNumber, Extension.getResult(screenNumber))
            }
        }
    }

    private fun setupResult(screenNumber: String, result: String) {
        mBinding.tvTotal.text = String.format("%.2f", result.toDouble())
        val data = DataEntity(query = screenNumber, value = result)
        viewModel.insert(data)
    }

    private fun backSpace() {
        if (screenNumber.isNotEmpty()) {
            screenNumber = screenNumber.substring(0, screenNumber.length - 1)
        }
        mBinding.tvInput.text = screenNumber
    }

    private fun updateNumber(num: String, isClear: Boolean) {
        if (isClear) {
            screenNumber = ""
            mBinding.tvTotal.text = ""
        } else {
            screenNumber += if (screenNumber.isNotEmpty()) {
                if (screenNumber.last().isDigit() && num.first().isDigit())
                    num
                else if (screenNumber.last().isDigit() || num.first().isDigit())
                    num
                else
                    return
            } else {
                if (num.first().isDigit()) {
                    num
                } else {
                    return
                }
            }
        }
        mBinding.tvInput.text = screenNumber
    }
}