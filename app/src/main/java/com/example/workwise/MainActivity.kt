package com.example.workwise

import JobSearchFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.workwise.fragments.ComposeFragments

import com.example.workwise.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item->
            var fragmentToShow: Fragment? = null
            when (item.itemId){
                R.id.action_home->{
                    fragmentToShow=JobSearchFragment()
                }
                R.id.action_compose->{
                    fragmentToShow=ComposeFragments()

                }
                R.id.action_profile->{
                    fragmentToShow=ProfileFragment()
                }


            }
            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            true
        }
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setSelectedItemId(R.id.action_compose)
    }



}