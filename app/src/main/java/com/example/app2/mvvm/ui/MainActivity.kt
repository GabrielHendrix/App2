package com.example.app2.mvvm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app2.R
import com.example.app2.databinding.ActivityMainBinding
import com.example.app2.mvvm.viewModel.MainViewModel


class MainActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var binding : ActivityMainBinding;
    lateinit var name: EditText
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loadName.setOnClickListener(this)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserver()

    }

    override fun onClick(view: View) {
        if (view.id == R.id.load_name)
        {
            name = findViewById(R.id.name) as EditText
            mainVM.registerUsername(this, name)
        }
    }
    private fun setObserver() {
        mainVM.isRegistered().observe(this, Observer {
            if(it){
//                Toast.makeText(this, R.string., Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Activity2::class.java))
            }
//            else {
//                Toast.makeText(this, , Toast.LENGTH_SHORT).show()
//            }
        })
    }
}




