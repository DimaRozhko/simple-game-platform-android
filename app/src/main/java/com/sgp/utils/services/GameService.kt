package com.sgp.utils.services

import android.os.StrictMode
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class GameService {
    private val client = OkHttpClient()

    fun signUp() : String {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())
        val jsonObject = JSONObject()
        val requestBody = jsonObject.toString().toRequestBody()
        val request = Request.Builder()
            .post(requestBody)
            .url("http://10.0.2.2:8080/game-process/start-time?startTime=15:02:11&email=name.surname@sgp.com")
            .build()
        //http://10.0.2.2/
        client.newCall(request).execute().use { response ->
            return response.body?.string().toString()
        }
    }
}