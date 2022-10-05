package com.example.calculator.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculator.data.db.DataEntity
import com.example.calculator.databinding.FragmentHistoryBinding
import com.example.calculator.ui.MainViewModel
import com.example.calculator.ui.fragment.adapter.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    val viewModel: MainViewModel by viewModels()
    lateinit var historyFragment: FragmentHistoryBinding
    private val mBinding get() = historyFragment
    private var historyData = ObservableArrayList<DataEntity>()
    lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        historyFragment = FragmentHistoryBinding.inflate(inflater, container, false)
        viewModel.getAllData()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callAllData()
        setAdapter()

    }

    private fun setAdapter() {
        adapter = HistoryAdapter(historyData)
        mBinding.rvHistory.adapter = adapter
        mBinding.rvHistory.layoutManager = LinearLayoutManager(context)
    }

    private fun callAllData() {
        viewModel.liveData.observe(viewLifecycleOwner) { data ->
            historyData.addAll(data)
            adapter.notifyData(historyData)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        private const val TAG = "HistoryFragment"
    }
}