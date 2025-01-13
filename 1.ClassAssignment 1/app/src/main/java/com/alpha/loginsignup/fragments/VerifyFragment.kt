package com.alpha.loginsignup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alpha.loginsignup.R
import com.alpha.loginsignup.databinding.FragmentVerifyBinding


class VerifyFragment : Fragment(R.layout.fragment_verify) {

    private var _binding: FragmentVerifyBinding? = null
    private val binding get() = _binding!!

    private var mail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerifyBinding.inflate(inflater, container, false)

        mail = arguments?.getString("MAIL") ?: ""

        binding.mailText.text = "Please enter the 6-digit code sent to your email $mail for verification."

        // Login button click action
        binding.btnVerify.setOnClickListener {
            // On successful login, navigate to the HomeFragment
            findNavController().navigate(R.id.action_signup_to_home)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}