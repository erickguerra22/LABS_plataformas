package com.erick.lab6erickguerra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var navBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(HomeFragment())
        navBar = findViewById(R.id.nav_mainActivity)
        setListeners()
    }

    private fun setListeners() {
        navBar.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.menu_option_home -> setFragment(HomeFragment())
                R.id.menu_option_search -> setFragment(SearchFragment())
                R.id.menu_option_library -> setFragment(LibraryFragment())
            }

            true
        }
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainer_mainActivity, fragment)
        }
    }

}