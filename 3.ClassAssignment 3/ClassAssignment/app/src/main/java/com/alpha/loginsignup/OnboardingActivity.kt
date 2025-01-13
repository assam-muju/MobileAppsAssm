package com.alpha.loginsignup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import android.widget.Button
import com.alpha.loginsignup.adapter.OnboardingFragmentAdapter
import com.alpha.loginsignup.databinding.ActivityOnboardingBinding


class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate ViewBinding for the activity layout
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPager


        // Set up ViewPager2 with the adapter
        val adapter = OnboardingFragmentAdapter(this)
        viewPager.adapter = adapter

        // Handle next button click
        binding.nextButton.setOnClickListener {
            if (viewPager.currentItem < 2) {
                viewPager.currentItem += 1
            } else {
                // Go to the Login Fragment (MainActivity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
