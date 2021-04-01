package com.example.ankitjadavpractical.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ankitjadavpractical.data.entity.Example
import com.example.ankitjadavpractical.data.repository.UserRepository

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<Example>? = null

    var example :  Example?=null
    fun getUser(offset : Int,limit : Int) : LiveData<Example>? {
        servicesLiveData?.postValue(null)
        servicesLiveData = UserRepository.getServicesApiCall(offset,limit)
        return servicesLiveData
    }



}