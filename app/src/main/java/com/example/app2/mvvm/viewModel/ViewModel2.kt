package com.example.app2.mvvm.viewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.app2.mvvm.ui.Activity2
import com.example.mvvm.repository.CatRepository
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
        val blogPost: Call<BlogPostEntity> = apiBlogService.getSinglePost(id)
        blogPost.enqueue(object : Callback<BlogPostEntity> {
            override fun onResponse(
                call: Call<BlogPostEntity>,
                response: Response<BlogPostEntity>
            ) {
                postText.value = response.body()?.id.toString()
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