package com.alpha.loginsignup

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.alpha.loginsignup.databinding.ActivitySplashBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the ViewBinding for the splash screen layout
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handler to delay the transition to the next activity
        Handler().postDelayed({
            // After the splash screen delay, navigate to the OnboardingActivity
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish() // Close the splash screen activity
        }, 2000) // Splash screen delay time (in milliseconds)
    }
}
