package com.example.app2.mvvm.repository.api.model
import com.google.gson.annotations.SerializedName

class BlogPostEntity {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("body")
    var body: String = ""
}