package com.alpha.loginsignup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alpha.loginsignup.databinding.FragmentSlide1Binding
import com.alpha.loginsignup.databinding.FragmentSlide2Binding
import com.alpha.loginsignup.databinding.FragmentSlide3Binding


class OnboardingFragment : Fragment() {

    private var _binding: ViewBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_POSITION = "position"

        // This function is used to pass the position of the slide
        fun newInstance(position: Int): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val position = arguments?.getInt(ARG_POSITION) ?: 0

        return when (position) {
            0 -> {
                // Inflate Slide 1 Layout
                _binding = FragmentSlide1Binding.inflate(inflater, container, false)
                binding.root
            }
            1 -> {
                // Inflate Slide 2 Layout
                _binding = FragmentSlide2Binding.inflate(inflater, container, false)
                binding.root
            }
            2 -> {
                // Inflate Slide 3 Layout
                _binding = FragmentSlide3Binding.inflate(inflater, container, false)
                binding.root
            }
            else -> {
                _binding = FragmentSlide1Binding.inflate(inflater, container, false)
                binding.root
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
