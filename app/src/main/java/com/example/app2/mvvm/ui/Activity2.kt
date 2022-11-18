package com.example.app2.mvvm.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View                                
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.app2.R
import com.example.app2.databinding.Activity2Binding
import androidx.lifecycle.Observer
import com.example.app2.mvvm.viewModel.ViewModel2


class Activity2 : AppCompatActivity() , View.OnClickListener, View.OnLongClickListener {

        private lateinit var binding : Activity2Binding;
        lateinit var name: TextView
        lateinit var phrase: TextView
        var cat: Boolean = false
        var dog: Boolean = false
        lateinit var catButton: ImageButton
        lateinit var dogButton: ImageButton
        private lateinit var VM2: ViewModel2

        @SuppressLint("SetTextI18n")
        override fun onCreate(savedInstanceState: Bundle?) {
                supportActionBar?.hide()
                super.onCreate(savedInstanceState)
                binding = Activity2Binding.inflate(layoutInflater)
                setContentView(binding.root)
                binding.cat.setOnClickListener(this)
                binding.dog.setOnClickListener(this)
                binding.generate.setOnClickListener(this)
                binding.cat.setOnLongClickListener(this)
                binding.dog.setOnLongClickListener(this)
                binding.generate.setOnLongClickListener(this)

                VM2 = ViewModelProvider(this).get(ViewModel2::class.java)

                name = findViewById(R.id.saved_name) as TextView
                name.setText(name.text.toString() + VM2.setText(this))
                setObserver()
        }
        private fun setObserver() {
                VM2.getPostText().observe(this, Observer {
                        if(it != "")
                                binding.name.text = it
                        else
                                binding.name.text = " "
                })
                VM2.isCat().observe(this, Observer {
                        if(it){
                                cat = true

                                binding.cat.setImageResource(R.drawable.caton)
                                Toast.makeText(this, R.string.cat_phrase, Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                                cat = false
                                binding.cat.setImageResource(R.drawable.cat)
                        }
                })
                VM2.isDog().observe(this, Observer {
                        if(it){
                                dog = true
                                binding.dog.setImageResource(R.drawable.dogon)
                                Toast.makeText(this, R.string.dog_phrase, Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                                dog = false
                                binding.dog.setImageResource(R.drawable.dog)
                        }
                })
        }

        override fun onClick(view: View) {
                if (view.id == R.id.cat)
                {
                        VM2.setStates(true, false)
                }
                if (view.id == R.id.dog)
                {
                        VM2.setStates(false, true)

                }
                if (view.id == R.id.generate){
//                        binding.name.setText(VM2.getPhrase())
                        var postId: String = ""
                        if (cat)
                        {
                                postId = (0 until 49).random().toString()
                        }
                        else if (dog)
                        {
                                postId = (50 until 99).random().toString()
                        }
                        if (postId == "")
                                Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
                        else
                                VM2.requestNewBlogPost(postId)
                }
        }
        override fun onLongClick(view: View): Boolean {
                if (view.id == R.id.generate)
                {
                        startActivity(Intent(this, MainActivity::class.java))
                }
                return true
        }
}
