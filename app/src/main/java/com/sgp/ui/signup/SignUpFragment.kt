package com.sgp.ui.signup

import android.os.Bundle
import android.text.TextUtils
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
import com.sgp.databinding.FragmentSignupBinding
import com.sgp.utils.ErrorsSGP
import com.sgp.utils.services.AuthService

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val signUpViewModel =
            ViewModelProvider(this).get(SignUpViewModel::class.java)

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textLogInView: TextView = binding.textSignupAlert
        val clickLogIn: Button = binding.buttonSignup
        clickLogIn.setOnClickListener(View.OnClickListener {
            if (!signUpViewModel.credValid.validationEmail(binding.emailInputField.text.toString())) {
                textLogInView.text = ErrorsSGP.INCORRECT_EMAIL.message
            }
            else if (!signUpViewModel.credValid.validationPassword(binding.passwordInputField.text.toString())) {
                textLogInView.text = ErrorsSGP.INCORRECT_PASSWORD.message
            }
            else if (checkUser(binding.usernameInputField.text.toString(),
                    binding.emailInputField.text.toString(),
                    binding.passwordInputField.text.toString())) {
                textLogInView.text = ErrorsSGP.USER_ALREADY_EXIST.message
            }
            else{
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

    private fun validationEmail(email: String) : Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validationPassword(password: String) : Boolean {
        return !TextUtils.isEmpty(password)
    }

    private fun checkUser(username: String, email: String, password: String) : Boolean {
        return AuthService().signUp(username, email, password)
    }
}