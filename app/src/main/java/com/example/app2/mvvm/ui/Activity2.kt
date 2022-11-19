package com.example.app2.mvvm.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View                                
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.app2.R
import com.example.app2.databinding.Activity2Binding
import androidx.lifecycle.Observer
import com.example.app2.mvvm.viewModel.ViewModel2
import com.example.app2.mvvm.repository.api.database.AppDatabase
import com.example.app2.mvvm.repository.api.model.ProductModel


class Activity2 : AppCompatActivity() , View.OnClickListener, View.OnLongClickListener {

        private lateinit var binding : Activity2Binding;
        lateinit var name: TextView
        var cat: Boolean = false
        var dog: Boolean = false
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
                binding.getById.setOnClickListener(this)
                VM2 = ViewModelProvider(this).get(ViewModel2::class.java)

                name = findViewById(R.id.saved_name) as TextView
                name.setText(name.text.toString() + VM2.setText(this))
                setObserver()
//                createEmptyDB()
        }
        private fun setObserver() {
                VM2.getPostText().observe(this, Observer {
                        if(it != "")
                        {
                                val id: Int = it.split("_")[0].toInt()
                                val phrase: String = it.split("_")[1]
                                binding.name.setText(phrase)
                                if (getDataByIdFromBD(id, "mydatabase.db") == "null")
                                        insertDataOnBD(id, phrase, "mydatabase.db")

                        }
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

        fun insertDataOnBD(id: Int, body: String, database: String) {
                val db = AppDatabase.getDatabase(this, database).ProductDAO()
//                db.deleteAll()
                val prod = ProductModel().apply{
                        this.id = id
                        this.body = body
                }

                if(db.insert(prod)>0){
                        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
                } else {
                        Toast.makeText(this, "Derrota", Toast.LENGTH_SHORT).show()
                }
        }
        fun updateDataOnBD(id: Int, body: String, database: String) {
                val db = AppDatabase.getDatabase(this, database).ProductDAO()
                val prod = ProductModel().apply{
                        this.id = id
                        this.body = body
                }

                if(db.update(prod)>0){
                        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
                } else {
                        Toast.makeText(this, "Derrota", Toast.LENGTH_SHORT).show()
                }
        }
        fun getDataByIdFromBD(id: Int, database: String): String {
                val db = AppDatabase.getDatabase(this, database).ProductDAO()

                val prod = db.getById(id)

                Toast.makeText(this, "FOI!", Toast.LENGTH_SHORT).show()
                if (prod != null)
                        return prod.body
                return "null"
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
                                postId = (1 until 60).random().toString()
                        }
                        else if (dog)
                        {
                                postId = (61 until 120).random().toString()
                        }
                        if (postId == "")
                                Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
                        else
                                VM2.requestNewBlogPost(postId)
                }
                if (view.id == R.id.getById) {
                        var phrase: String = "null"
                        while (phrase == "null")
                        {
                                if (cat) {
                                        phrase = getDataByIdFromBD((1 until 60).random(), "mydatabase.db")
                                } else if (dog) {
                                        phrase = getDataByIdFromBD((61 until 120).random(), "mydatabase.db")
                                }
                        }
                        binding.name.setText(phrase)
//                        Toast.makeText(this, "FOI!", Toast.LENGTH_SHORT).show()
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
