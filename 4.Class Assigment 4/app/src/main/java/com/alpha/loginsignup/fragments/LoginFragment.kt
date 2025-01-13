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
import androidx.room.Room
import com.alpha.loginsignup.R
import com.alpha.loginsignup.database.AppDatabase
import com.alpha.loginsignup.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        with(binding) {
          /*  // Login button click action
            binding.btnLogin.setOnClickListener {
                val email = mailEditText.text.toString()
                val password = passwordEditText.text.toString()

                val isValid = validateLoginInputs(mailEditText, passwordEditText)

                if (isValid) {
                    CoroutineScope(Dispatchers.IO).launch {
                        loginWithCredentials(email, password)
                    }

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please correct the errors",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

           */

            // Sign up button click action
            binding.btnSignup.setOnClickListener {
                findNavController().navigate(R.id.action_login_to_signup)
            }
        }

        return binding.root
    }

    private suspend fun loginWithCredentials(email: String, password: String) {
        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "user-database"
        ).build()

        // Check if credentials match an existing user
        val user = db.userDao().getUserByEmail(email)

        if (user != null && user.password == password) {
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                // Navigate to the next screen
                findNavController().navigate(R.id.action_login_to_a)
            }
        } else {
            withContext(Dispatchers.Main) {
                // Show error if credentials are invalid
                Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun validateLoginInputs(
        emailEditText: EditText,
        passwordEditText: EditText
    ): Boolean {
        var isValid = true

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!emailEditText.text.toString().matches(emailPattern.toRegex())) {
            emailEditText.error = "Please enter a valid email"
            isValid = false
        }

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
