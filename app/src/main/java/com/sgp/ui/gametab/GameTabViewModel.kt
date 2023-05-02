package com.sgp.ui.gametab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameTabViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply{
        value = "This is game tab Fragment"
    }
    val text: LiveData<String> = _text
}