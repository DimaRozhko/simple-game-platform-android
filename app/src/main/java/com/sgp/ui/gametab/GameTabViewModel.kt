package com.sgp.ui.gametab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sgp.utils.ConstantsSGP

class GameTabViewModel constructor() : ViewModel() {

    private val _gameTable = Array(4) {
            i -> Array(4) {
                j -> MutableLiveData<String>().apply{
                    val itemValue: String = (4 * i + j + 1).toString()
                    value = if ("16".equals(itemValue)) ConstantsSGP.EMPTY.value.toString() else itemValue
                }
            }
        }
    val gameTable = _gameTable
}