package com.example.app2.mvvm.viewModel

import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app2.R
import com.example.app2.mvvm.repository.Preferences
import com.example.mvvm.repository.CatRepository
import com.example.app2.mvvm.ui.Activity2

class ViewModel2: ViewModel() {
    private var dog = MutableLiveData<Boolean>(false)
    private var cat = MutableLiveData<Boolean>(false)

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

    fun getPhrase(): String{
        if(cat.value == true)
            return CatRepository().generatePhrase()
        else if (dog.value == true)
            return "frase cao"
        return "null"
    }
}