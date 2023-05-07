package com.sgp.ui.userinf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.sgp.databinding.FragmentUserInformationBinding

class UserInformationFragment : Fragment() {

    private var _binding: FragmentUserInformationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userInformationViewModel =
            ViewModelProvider(this).get(UserInformationViewModel::class.java)

        _binding = FragmentUserInformationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val favoriteGameTextView: TextView = binding.textFavoriteGameValue
        userInformationViewModel.favorite_game.observe(viewLifecycleOwner) {
            favoriteGameTextView.text = it
        }

        val bestTimeTextView: TextView = binding.textBestTimeValue
        userInformationViewModel.best_time.observe(viewLifecycleOwner) {
            bestTimeTextView.text = it
        }

        val usernameInputView: TextInputEditText = binding.usernameInputField
        userInformationViewModel.default_username.observe(viewLifecycleOwner) {
            usernameInputView.setText(it)
        }

        val emailInputView: TextInputEditText = binding.emailInputField
        userInformationViewModel.default_email.observe(viewLifecycleOwner) {
            emailInputView.setText(it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}