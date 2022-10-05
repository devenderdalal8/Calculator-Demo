package com.example.calculator.ui.fragment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.data.db.DataEntity
import com.example.calculator.databinding.ItemHistoryBinding

class HistoryAdapter constructor(val data: ObservableArrayList<DataEntity>) :
    RecyclerView.Adapter<HistoryViewModel>() {
    lateinit var context: Context
    fun notifyData( data: ObservableArrayList<DataEntity>){
        this.data.addAll(data)
        Log.d(TAG, "notifyData: $data")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewModel {
        context = parent.context
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return HistoryViewModel(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewModel, position: Int) {
        val dataPos = data[position]
        Log.d("TAG", "onBindViewHolder: $data")
        holder.bind(dataPos , position)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${data.size}")
        return data.size
    }
    companion object{
        private const val TAG = "HistoryAdapter"
    }
}

class HistoryViewModel(private val item: ItemHistoryBinding) : RecyclerView.ViewHolder(item.root) {
    fun bind(dataEntity: DataEntity ,position: Int){
        item.tvEqual.text = String.format("= %s",dataEntity.value)
        item.tvQuery.text = dataEntity.query
    }
}