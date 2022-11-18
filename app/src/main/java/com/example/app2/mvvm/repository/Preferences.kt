package com.example.app2.mvvm.repository

import android.content.Context
import android.content.SharedPreferences

class Preferences (context: Context) {
    private val sp: SharedPreferences =
        context.getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)

    fun setString(key: String, str: String): Boolean {
        sp.edit().putString(key, str).apply()
        return (key != "")
    }

    fun getString(key: String): String {
        return sp.getString(key, "") ?: ""
    }
//
//    fun register(key: String): Boolean {
//        return (key != "")
//    }
}