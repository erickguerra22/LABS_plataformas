package com.erick.lab7erickguerra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val validEmails = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        validEmails.add("jcdurini@uvg.edu.gt")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addValidEmail(email: String){
        validEmails.add(email)
    }

    fun getValidEmails(): ArrayList<String> {
        return validEmails
    }
}