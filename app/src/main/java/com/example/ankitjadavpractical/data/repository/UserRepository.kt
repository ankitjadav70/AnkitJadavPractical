package com.example.ankitjadavpractical.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ankitjadavpractical.data.entity.Example
import com.example.ankitjadavpractical.data.network.NetworkService
import com.example.ankitjadavpractical.data.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {

    val serviceSetterGetter = MutableLiveData<Example>()

    fun getServicesApiCall(offset : Int,limit : Int): MutableLiveData<Example> {

        val call = ServiceBuilder.buildService(NetworkService::class.java).getUsers(offset,limit)
        call.enqueue(object: Callback<Example> {
            override fun onFailure(call: Call<Example>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<Example>,
                response: Response<Example>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                val msg = data!!.message

                serviceSetterGetter.value = data!!
            }
        })

        return serviceSetterGetter
    }



}