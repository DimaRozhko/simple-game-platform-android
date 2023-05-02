package com.sgp.ui.gameinf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sgp.databinding.FragmentGameInformationBinding
import com.sgp.databinding.FragmentUserInformationBinding

class GameInformationFragment : Fragment() {

    private var _binding: FragmentGameInformationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel =
            ViewModelProvider(this).get(GameInformationViewModel::class.java)

        _binding = FragmentGameInformationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val gameNameView: TextView = binding.textGameNameValue
        loginViewModel.game_name.observe(viewLifecycleOwner) {
            gameNameView.text = it
        }

        val gameRuleView: TextView = binding.textRuleValue
        loginViewModel.game_rule.observe(viewLifecycleOwner) {
            gameRuleView.text = it
        }

        val gameHistoryView: TextView = binding.textHistoryValue
        loginViewModel.game_history.observe(viewLifecycleOwner) {
            gameHistoryView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}