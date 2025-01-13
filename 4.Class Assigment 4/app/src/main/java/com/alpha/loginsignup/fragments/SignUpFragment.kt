package com.alpha.loginsignup.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.alpha.loginsignup.R
import com.alpha.loginsignup.database.AppDatabase
import com.alpha.loginsignup.database.User
import com.alpha.loginsignup.databinding.FragmentSignupBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpFragment : Fragment(R.layout.fragment_signup) {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)


        with(binding) {
            // Login button click action
            btnLogin.setOnClickListener {
                // Navigate to the login screen
                findNavController().navigate(R.id.action_signup_to_login)
            }

            // Sign up button click action
            btnSignup.setOnClickListener {
                val isValid = validateInputs(
                    nameEditText, mailEditText, phoneEditText, passwordEditText
                )

                if (isValid) {
                    val email = mailEditText.text.toString()
                    val password = passwordEditText.text.toString()

                    // Save credentials securely using Credential Manager
                    CoroutineScope(Dispatchers.IO).launch {
                        saveCredentials(email, password)
                    }

                } else {
                    Toast.makeText(
                        requireContext(), "Please correct the errors", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return binding.root
    }

    private suspend fun saveCredentials(email: String, password: String) {
        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "user-database"
        ).build()

        // Check if a user with the same email already exists
        val existingUser = db.userDao().getUserByEmail(email)

        if (existingUser != null) {
            withContext(Dispatchers.Main) {
                // Show error if user already exists
                Toast.makeText(
                    requireContext(),
                    "An account with this email already exists",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            // Save new user credentials
            val user = User(email = email, password = password)
            db.userDao().insert(user)

            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Account created successfully", Toast.LENGTH_SHORT).show()

                // Navigate to the verification page only after saving the new user
                val bundle = Bundle().apply {
                    putString("MAIL", email)
                }
                findNavController().navigate(R.id.action_signup_to_verify, bundle)
            }
        }
    }





    private fun validateInputs(
        nameEditText: EditText,
        mailEditText: EditText,
        phoneEditText: EditText,
        passwordEditText: EditText
    ): Boolean {
        var isValid = true

        if (TextUtils.isEmpty(nameEditText.text)) {
            nameEditText.error = "Please enter your full name"
            isValid = false
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!mailEditText.text.toString().matches(emailPattern.toRegex())) {
            mailEditText.error = "Please enter a valid email"
            isValid = false
        }

     /*   val phonePattern = "^[0-9]{10}$"
        if (!phoneEditText.text.toString().matches(phonePattern.toRegex())) {
            phoneEditText.error = "Please enter a valid 10-digit phone number"
            isValid = false
        }

      */

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
