package com.sgp.utils.services

import android.os.StrictMode
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AuthService {
    private val client = OkHttpClient()

    fun logIn(email: String, password: String) : Boolean {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())
        val request = Request.Builder()
            .get()
            .url("http://10.0.2.2:8080/auth/log-in?email=$email&password=$password")
            .build()
        //http://10.0.2.2/
        client.newCall(request).execute().use { response ->
            return response.code == 200
        }
    }

    fun signUp(username: String, email: String, password: String) : Boolean {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())
        val jsonObject = JSONObject()
//        {
//            "email": "name.surname@sgp.com",
//            "username": "NameSurname",
//            "password": "name1234"
//        }
        jsonObject.put("username", username)
        jsonObject.put("email", email)
        jsonObject.put("password", password)
        val requestBody = jsonObject.toString().toRequestBody()
        val request = Request.Builder()
            .post(requestBody)
            .url("http://10.0.2.2:8080/auth/sign-up")
            .build()
        //http://10.0.2.2/
        client.newCall(request).execute().use { response ->
            return response.code == 200
        }
    }
}