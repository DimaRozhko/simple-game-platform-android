package com.sgp.utils

import android.text.TextUtils

class CredentialsValidationUtils {

    fun validationEmail(email: String) : Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validationPassword(password: String) : Boolean {
        return !TextUtils.isEmpty(password)
    }
}