package com.erick.lab7erickguerra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val rightNow = Calendar.getInstance()
    private val currentHour: Int = rightNow.get(Calendar.HOUR_OF_DAY)

    private lateinit var txtTitle : TextView
    private lateinit var btnUpdate : Button
    private lateinit var txtMessage : TextView
    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtTitle = view.findViewById(R.id.txt_homeFragment_title)
        btnUpdate = view.findViewById(R.id.btn_homeFragment_updateProfile)
        txtMessage = view.findViewById(R.id.txt_homeFragment_message)

        txtMessage.text = "Hola ${args.email}, ¡necesitamos que actualices tu perfil!"

        txtTitle.text = when(currentHour){
            in 4..11 -> "¡Buenos días!"
            in 12..18 -> "¡Buenas tardes!"
            else -> "¡Buenas noches!"
        }

        setListeners()
    }

    private fun setListeners() {
        btnUpdate.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMyProfileFragment(
                args.email
            )
            requireView().findNavController().navigate(action)
        }
    }
}