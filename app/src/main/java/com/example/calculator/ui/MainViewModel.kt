package com.example.calculator.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.data.db.CalculatorDao
import com.example.calculator.data.db.DataEntity
import com.example.calculator.domain.usecase.GetSavedNewsUseCase
import com.example.calculator.domain.usecase.SaveDataUseCase
import com.example.calculator.util.Utility
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    val calculatorDao: CalculatorDao,
    val savedNewsUseCase: GetSavedNewsUseCase,
    private val saveDataUseCase: SaveDataUseCase
) : ViewModel() {

    val loginNumber = "9876543210"
    val otp = "111111"
    private val _liveData = MutableLiveData<List<DataEntity>>()
    val liveData get() = _liveData

    fun insert(dataEntity: DataEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (Utility.isNetworkAvailable(context)) {
                    saveDataUseCase.execute(dataEntity)
                } else {
                    Log.e("TAG", "insert: ")
                }
            } catch (ex: Exception) {
                Log.e("TAG", "insert: $ex")
            }
        }
    }

    fun getAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            _liveData.postValue(calculatorDao.getAllData())
        }
    }

}