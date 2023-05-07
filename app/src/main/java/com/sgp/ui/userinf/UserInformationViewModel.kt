package com.sgp.ui.userinf

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInformationViewModel : ViewModel() {

    private val _favorite_game = MutableLiveData<String>().apply{
        value = "\'15\'"
    }
    val favorite_game: LiveData<String> = _favorite_game

    private val _best_time = MutableLiveData<String>().apply{
        value = "00:17:04"
    }
    val best_time: LiveData<String> = _best_time

    private val _default_username = MutableLiveData<String>().apply{
        value = "SuperGamer"
    }
    val default_username: LiveData<String> = _default_username

    private val _default_email = MutableLiveData<String>().apply{
        value = "super.gamer@gmail.com"
    }
    val default_email: LiveData<String> = _default_email
}