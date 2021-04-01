package com.example.ankitjadavpractical.data.network

import com.example.ankitjadavpractical.data.entity.Example
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("api/users")
    fun getUsers(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<Example>

}