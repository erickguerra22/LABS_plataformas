package com.erick.lab5erickguerra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btn_mainActivity_iniciar)
        val name = "Erick Stiv Junior Guerra Muñoz"
        button.setOnClickListener{
            val toastMsg = Toast.makeText(applicationContext, name, Toast.LENGTH_SHORT)
            toastMsg.show()
        }
    }
}