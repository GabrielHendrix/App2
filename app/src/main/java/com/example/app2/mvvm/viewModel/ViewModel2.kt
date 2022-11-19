package com.example.app2.mvvm.viewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.app2.mvvm.ui.Activity2
import com.example.app2.mvvm.repository.Preferences
import com.example.app2.mvvm.repository.api.model.BlogPostEntity
import com.example.app2.mvvm.repository.api.client.ClientRetrofit
import com.example.app2.mvvm.repository.api.service.BlogPostService

class ViewModel2: ViewModel() {
    private var dog = MutableLiveData<Boolean>(false)
    private var cat = MutableLiveData<Boolean>(false)

    private var postText = MutableLiveData<String>()

    fun getPostText(): LiveData<String>{
        return postText
    }

    fun requestNewBlogPost(id: String) {
        val apiBlogService = ClientRetrofit.createService(BlogPostService::class.java)
        var blogPost: Call<BlogPostEntity> = apiBlogService.getSingleCatPost(id)
        if (dog.value == true)
            blogPost = apiBlogService.getSingleDogPost(id)

        blogPost.enqueue(object : Callback<BlogPostEntity> {
            override fun onResponse(
                call: Call<BlogPostEntity>,
                response: Response<BlogPostEntity>
            ) {
                postText.value = id + "_" + response.body()?.body
            }

            override fun onFailure(call: Call<BlogPostEntity>, t: Throwable) {
                postText.value = "Falha na requisição da API"
            }
        })
    }
    fun isCat(): LiveData<Boolean>{
        return cat
    }
    fun isDog(): LiveData<Boolean>{
        return dog
    }
    fun setStates(catState: Boolean, dogState: Boolean){
        cat.value = catState
        dog.value = dogState
    }

    fun setText(context: Activity2): String{
        return (", " + Preferences(context).getString("name"))
    }
}