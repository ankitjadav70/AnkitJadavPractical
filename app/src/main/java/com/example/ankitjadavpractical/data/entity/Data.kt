package com.example.ankitjadavpractical.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("users")
    @Expose
    var users: List<User>? =
        null

    @SerializedName("has_more")
    @Expose
    var hasMore: Boolean? = null

}