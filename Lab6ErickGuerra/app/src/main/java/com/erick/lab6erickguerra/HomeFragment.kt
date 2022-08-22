package com.erick.lab6erickguerra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var txtSalute: TextView

    private val rightNow = Calendar.getInstance()
    private val currentHour: Int = rightNow.get(Calendar.HOUR_OF_DAY)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txtSalute = view.findViewById(R.id.txt_fragmentHome_salute)

        txtSalute.text = when(currentHour){
            in 1..11 -> "Good morning,"
            in 12..18 -> "Good afternoon,"
            else -> "Good night,"
        }

    }
}