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
import com.alpha.loginsignup.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        with(binding) {
         /*   // Login button click action
            binding.btnLogin.setOnClickListener {
                validateLoginInputs(mailEditText, passwordEditText)

                val isValid = validateLoginInputs(
                    binding.mailEditText,
                    binding.passwordEditText
                )

                if (isValid) {
                    // Proceed with login logic
                    Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                    // On successful login, navigate to the HomeFragment
                    findNavController().navigate(R.id.action_login_to_home)
                } else {
                    // Validation failed, show a warning
                    Toast.makeText(
                        requireContext(),
                        "Please correct the errors",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            // Sign up button click action
            binding.btnSignup.setOnClickListener {
                // Navigate to SignUpFragment
                findNavController().navigate(R.id.action_login_to_signup)
            }   */
        }



        return binding.root
    }

    fun validateLoginInputs(
        emailEditText: EditText,
        passwordEditText: EditText
    ): Boolean {
        var isValid = true

        // Validate email using a regex pattern
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!emailEditText.text.toString().matches(emailPattern.toRegex())) {
            emailEditText.error = "Please enter a valid email"
            isValid = false
        }

        // Validate password (non-empty)
        if (TextUtils.isEmpty(passwordEditText.text)) {
            passwordEditText.error = "Password cannot be empty"
            isValid = false
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
