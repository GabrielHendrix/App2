package com.example.app2.mvvm.repository.api.service

import com.example.app2.mvvm.repository.api.model.BlogPostEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogPostService {

    @GET("cats/{postId}")
    fun getSingleCatPost(@Path("postId")postId: String): Call<BlogPostEntity>

    @GET("dogs/{postId}")
    fun getSingleDogPost(@Path("postId")postId: String): Call<BlogPostEntity>
}