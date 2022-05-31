package com.example.projetosmarttools.Util

import android.content.Context
import android.content.SharedPreferences
import com.example.projetosmarttools.R

class SessionManager (context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "token"
    }
    /**
     * Função para salvar token de autenticação
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }
    /**
     * Função para buscar token de autenticação
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}