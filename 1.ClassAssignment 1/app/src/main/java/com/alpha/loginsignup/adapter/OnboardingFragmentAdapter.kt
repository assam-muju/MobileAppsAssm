package com.alpha.loginsignup.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alpha.loginsignup.fragments.OnboardingFragment


class OnboardingFragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3  // Number of slides

    override fun createFragment(position: Int): Fragment {
        return OnboardingFragment.newInstance(position)  // Create fragment for the given slide position
    }
}
