package com.sgp.ui.gametab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.sgp.databinding.FragmentGameTabBinding
import com.sgp.utils.ConstantsSGP

class GameTabFragment : Fragment() {

    private var _binding: FragmentGameTabBinding? = null

    private val binding get() = _binding!!

    private var _gameTabViewModel: GameTabViewModel? = null
    private val gameTabViewModel get() = _gameTabViewModel!!

    private lateinit var previousItem: TableItemModel

    private var startGame: Boolean = false

    private var congratulationGame: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _gameTabViewModel = ViewModelProvider(this).get(GameTabViewModel::class.java)

        _binding = FragmentGameTabBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setButtonItemTable(binding.button1GameTable, 0, 0)
        setButtonItemTable(binding.button2GameTable, 0, 1)
        setButtonItemTable(binding.button3GameTable, 0, 2)
        setButtonItemTable(binding.button4GameTable, 0, 3)

        setButtonItemTable(binding.button5GameTable, 1, 0)
        setButtonItemTable(binding.button6GameTable, 1, 1)
        setButtonItemTable(binding.button7GameTable, 1, 2)
        setButtonItemTable(binding.button8GameTable, 1, 3)

        setButtonItemTable(binding.button9GameTable, 2, 0)
        setButtonItemTable(binding.button10GameTable, 2, 1)
        setButtonItemTable(binding.button11GameTable, 2, 2)
        setButtonItemTable(binding.button12GameTable, 2, 3)

        setButtonItemTable(binding.button13GameTable, 3, 0)
        setButtonItemTable(binding.button14GameTable, 3, 1)
        setButtonItemTable(binding.button15GameTable, 3, 2)
        setButtonItemTable(binding.button16GameTable, 3, 3)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setButtonItemTable(button: Button, rowIdx: Int, colIdx: Int) {
        gameTabViewModel.gameTable.get(rowIdx).get(colIdx).observe(viewLifecycleOwner) {
            button.text = it
        }
        button.setOnClickListener(View.OnClickListener {
            if (startGame) {
                var previousRowIdx: Int = 0
                var previousColumnIdx: Int = 0
                for ((searchRowIdx, searchRowValue) in gameTabViewModel.gameTable.withIndex()) {
                    for ((searchColIdx, searchColValue) in searchRowValue.withIndex()) {
                        if (searchColValue.value == previousItem?.button?.text) {
                            previousRowIdx = searchRowIdx
                            previousColumnIdx = searchColIdx
                            break
                        }
                    }
                }

                if ((previousRowIdx - rowIdx >= -1 && previousRowIdx - rowIdx <= 1)
                    && (previousColumnIdx - colIdx >= -1 && previousColumnIdx - colIdx <= 1)
                    && (previousColumnIdx == colIdx || previousRowIdx == rowIdx)
                ) {

                    if (ConstantsSGP.EMPTY.value.toString() == previousItem.button.text) {
                        val savedButtonString: String =
                            gameTabViewModel.gameTable[previousItem.rowIndex][previousItem.columnIndex].value.toString()
                        gameTabViewModel.gameTable[previousItem.rowIndex][previousItem.columnIndex].value =
                            gameTabViewModel.gameTable[rowIdx][colIdx].value.toString()
                        gameTabViewModel.gameTable[previousItem.rowIndex][previousItem.columnIndex].observe(
                            viewLifecycleOwner
                        ) {
                            previousItem.button.text = it
                        }
                        gameTabViewModel.gameTable[previousItem.rowIndex][previousItem.columnIndex].value =
                            savedButtonString
                        gameTabViewModel.gameTable[rowIdx][colIdx].observe(viewLifecycleOwner) {
                            button.text = it
                        }
                    } else if (ConstantsSGP.EMPTY.value.toString() == button.text.toString()) {
                        val savedButtonString: String =
                            gameTabViewModel.gameTable[rowIdx][colIdx].value.toString()
                        gameTabViewModel.gameTable[rowIdx][colIdx].value =
                            gameTabViewModel.gameTable[previousItem.rowIndex][previousItem.columnIndex].value.toString()
                        gameTabViewModel.gameTable[rowIdx][colIdx].observe(viewLifecycleOwner) {
                            button.text = it
                        }
                        gameTabViewModel.gameTable[previousItem.rowIndex][previousItem.columnIndex].value =
                            savedButtonString
                        gameTabViewModel.gameTable[previousItem.rowIndex][previousItem.columnIndex].observe(
                            viewLifecycleOwner
                        ) {
                            previousItem.button.text = it
                        }
                    }
                }
                previousItem = TableItemModel(button, rowIdx, colIdx)

                var counterOrder = 1
                for (searchRowValue in gameTabViewModel.gameTable) {
                    for (searchColValue in searchRowValue) {
                        if (searchColValue.value?.toInt() == counterOrder) {
                            counterOrder++
                        }
                    }
                }
                if (counterOrder == 15) {
                    congratulationGame = true
                }
            }
            else if(ConstantsSGP.EMPTY.value.toString() == button.text.toString()) {
                startGame = true
                val searchValues = arrayOf(
                    arrayOf(MutableLiveData<String>().apply{ value = "10" },
                        MutableLiveData<String>().apply{ value = "14" },
                        MutableLiveData<String>().apply{ value = "13" },
                        MutableLiveData<String>().apply{ value = "5" }),
                    arrayOf(MutableLiveData<String>().apply{ value = "9" },
                        MutableLiveData<String>().apply{ value = "6" },
                        MutableLiveData<String>().apply{ value = "1" },
                        MutableLiveData<String>().apply{ value = "15" }),
                    arrayOf(MutableLiveData<String>().apply{ value = "12" },
                        MutableLiveData<String>().apply{ value = "8" },
                        MutableLiveData<String>().apply{ value = ConstantsSGP.EMPTY.value.toString() },
                        MutableLiveData<String>().apply{ value = "4" }),
                    arrayOf(MutableLiveData<String>().apply{ value = "2" },
                        MutableLiveData<String>().apply{ value = "3" },
                        MutableLiveData<String>().apply{ value = "7" },
                        MutableLiveData<String>().apply{ value = "11" }))
                for ((searchRowIdx, searchRowValue) in gameTabViewModel.gameTable.withIndex()) {
                    for ((searchColIdx, searchColValue) in searchRowValue.withIndex()) {
                        searchColValue.value = searchValues[searchRowIdx][searchColIdx].value
                    }
                }
            }
            if (congratulationGame) {
                binding.textViewCongratulation.text = ConstantsSGP.CONGRATULATION.value.toString()
                binding.textViewWinner.text = ConstantsSGP.YOU_WINNER.value.toString()
                congratulationGame = false
            }
            else {
                binding.textViewCongratulation.text = ConstantsSGP.EMPTY.value.toString()
                binding.textViewWinner.text = ConstantsSGP.EMPTY.value.toString()
            }
        })
        previousItem = TableItemModel(button, rowIdx, colIdx)
    }
}