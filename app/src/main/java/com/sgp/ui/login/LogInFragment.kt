package com.sgp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sgp.MainActivity
import com.sgp.R
import com.sgp.databinding.FragmentLoginBinding
import com.sgp.utils.ErrorsSGP
import com.sgp.utils.services.AuthService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class LogInFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel =
            ViewModelProvider(this).get(LogInViewModel::class.java)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textLogInView: TextView = binding.textLoginAlert
        val clickLogIn: Button = binding.buttonLogin

        clickLogIn.setOnClickListener(View.OnClickListener {
            if (!loginViewModel.credValid.validationEmail(binding.emailInputField.text.toString())) {
                textLogInView.text = ErrorsSGP.INCORRECT_EMAIL.message
            }
            else if (!loginViewModel.credValid.validationPassword(binding.passwordInputField.text.toString())) {
                textLogInView.text = ErrorsSGP.INCORRECT_PASSWORD.message
            }
            else if (!checkUser(binding.emailInputField.text.toString(), binding.passwordInputField.text.toString())) {
                textLogInView.text = ErrorsSGP.UNKNOWN_USER.message
            }
            else {
                textLogInView.text = ""
                (activity as MainActivity?)!!.activateToGameState()
                findNavController().navigate(R.id.navigation_user_inf)
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkUser(email: String, password: String) : Boolean {
        return AuthService().logIn(email, password)
    }
}