package com.alpha.loginsignup.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alpha.loginsignup.R
import com.alpha.loginsignup.databinding.FragmentSignupBinding

class SignUpFragment : Fragment(R.layout.fragment_signup) {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        // Login button click action
        binding.btnLogin.setOnClickListener {
            // On successful login, navigate to the HomeFragment
            findNavController().navigate(R.id.action_signup_to_login)
        }



        with(binding){

            // Sign up button click action
            binding.btnSignup.setOnClickListener {
                validateInputs(nameEditText, mailEditText, phoneEditText, passwordEditText)
                val isValid = validateInputs(
                    binding.nameEditText,
                    binding.mailEditText,
                    binding.phoneEditText,
                    binding.passwordEditText
                )


                if (isValid) {
                    val bundle = Bundle().apply {
                        putString("MAIL", binding.mailEditText.text.toString())
                    }
                    // Proceed to the next screen
                    Toast.makeText(requireContext(), "All inputs are valid!", Toast.LENGTH_SHORT).show()
                    // Navigate to SignUpFragment
                    findNavController().navigate(R.id.action_signup_to_verify, bundle)
                } else {
                    // Inputs are invalid, show a warning
                    Toast.makeText(requireContext(), "Please correct the errors", Toast.LENGTH_SHORT).show()
                }

            }

        }


        return binding.root
    }


    private fun validateInputs(
        nameEditText: EditText,
        mailEditText: EditText,
        phoneEditText: EditText,
        passwordEditText: EditText
    ): Boolean {
        var isValid = true

        // Validate name (non-empty)
        if (TextUtils.isEmpty(nameEditText.text)) {
            nameEditText.error = "Please enter your full name"
            isValid = false
        }

        // Validate email using a regex pattern
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!mailEditText.text.toString().matches(emailPattern.toRegex())) {
            mailEditText.error = "Please enter a valid email"
            isValid = false
        }

        // Validate phone number (10 digits)
        val phonePattern = "^[0-9]{10}$"
        if (!phoneEditText.text.toString().matches(phonePattern.toRegex())) {
            phoneEditText.error = "Please enter a valid 10-digit phone number"
            isValid = false
        }

        // Validate password (min 6 characters)
        if (passwordEditText.text.length < 6) {
            passwordEditText.error = "Password must be at least 6 characters"
            isValid = false
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}