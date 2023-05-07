package com.sgp.utils.services

import android.os.StrictMode
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class GameService {
    private val client = OkHttpClient()

    fun setStartTime() : String {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())
        val jsonObject = JSONObject()
        val requestBody = jsonObject.toString().toRequestBody()
        val request = Request.Builder()
            .header("Authorization", Credentials.basic("admin", "1"))
            .post(requestBody)
            .url("http://10.0.2.2:8000/game-process/start-time?startTime=15:02:11&id=1")
            .build()
        //http://10.0.2.2/
        client.newCall(request).execute().use { response ->
            return response.body?.string().toString()
        }
    }
}