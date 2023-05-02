package com.sgp.ui.gameinf

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameInformationViewModel : ViewModel() {

    private val _game_name = MutableLiveData<String>().apply{
        value = "\'15\'"
    }
    val game_name: LiveData<String> = _game_name

    private val _game_rule = MutableLiveData<String>().apply{
        value = "move items of play table to create a sequence 1, 2, 3, 4, ... , 12, 13, 14, 15"
    }
    val game_rule: LiveData<String> = _game_rule

    private val _game_history = MutableLiveData<String>().apply{
        value = "popular puzzle invented in 1878 by Noah Chapman. The puzzle is a set of 15 identical square bones with numbers printed on them, lying in a square box."
    }
    val game_history: LiveData<String> = _game_history
}