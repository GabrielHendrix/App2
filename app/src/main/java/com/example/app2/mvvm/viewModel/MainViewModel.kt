package com.example.app2.mvvm.viewModel

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app2.mvvm.repository.Preferences

import com.example.app2.mvvm.ui.MainActivity

class MainViewModel : ViewModel() {

    private var register = MutableLiveData<Boolean>()

    fun isRegistered(): LiveData<Boolean> {
        return register
    }

    fun registerUsername(context: MainActivity, name: EditText) {
        register.value = Preferences(context).setString("name", name.text.toString())
    }

}