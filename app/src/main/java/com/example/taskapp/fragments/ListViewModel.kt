package com.example.taskapp.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.models.GetPhone
import com.example.taskapp.repository.NetworkRepository
import com.example.taskapp.responseApis.ResApis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: NetworkRepository) : ViewModel() {

    val smartPhoneList = MutableLiveData<ResApis<GetPhone>>(ResApis.Loading())

    fun phoneList() = viewModelScope.launch {
        try {
            repository.getList().let {
                if (it.isSuccessful) {
                    smartPhoneList.postValue(ResApis.Success(it.body()!!))
                } else {

                    smartPhoneList.postValue(ResApis.Error(it.message()))
                }
            }
        } catch (e: Exception) {
            smartPhoneList.postValue(ResApis.Error(e.message.toString()))
        }
    }
}