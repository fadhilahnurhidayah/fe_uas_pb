package com.example.uaspmob.util

import android.content.Context
import android.content.SharedPreferences
import com.example.uaspmob.model.User
import com.google.gson.Gson

class SharedPrefManager(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()
    private val gson = Gson()

    fun saveUser(user: User) {
        editor.putInt(Constants.USER_ID, user.id)
        editor.putString(Constants.USER_USERNAME, user.username)
        editor.putString(Constants.USER_ROLE, user.role)
        editor.apply()
    }

    fun getUser(): User? {
        val id = preferences.getInt(Constants.USER_ID, -1)
        if (id == -1) return null

        val nama = preferences.getString(Constants.USER_NAME, "") ?: ""
        val username = preferences.getString(Constants.USER_USERNAME, "") ?: ""
        val role = preferences.getString(Constants.USER_ROLE, "") ?: ""

        return User(id, username, "", role)
    }

    fun clear() {
        editor.clear()
        editor.apply()
    }
}
