package com.erick.lab7erickguerra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var btnLogin : Button
    private lateinit var txtNewAccount : TextView
    private lateinit var inputEmail : TextInputLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin = view.findViewById(R.id.btn_loginFragment_login)
        txtNewAccount = view.findViewById(R.id.txt_loginFragment_newUser)
        inputEmail = view.findViewById(R.id.inputLayout_loginFragment_email)

        setListeners()
    }

    private fun setListeners() {
        btnLogin.setOnClickListener {

            val email = inputEmail.editText!!.text.toString().trim()

            if((activity as MainActivity).getValidEmails().contains(email)) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                    email
                )
                requireView().findNavController().navigate(action)
            } else {
                val toastMsg = Toast.makeText(activity, "El email ingresado no es válido o no existe.", Toast.LENGTH_SHORT)
                toastMsg.show()
            }
        }

        txtNewAccount.setOnClickListener {
            requireView().findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToNewAccountFragment()
            )
        }
    }
}