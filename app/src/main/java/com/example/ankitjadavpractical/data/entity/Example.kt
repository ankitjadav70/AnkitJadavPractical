package com.example.ankitjadavpractical.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: Any? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

}