package com.erick.lab7erickguerra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout

class NewAccountFragment : Fragment(R.layout.fragment_new_account) {
    private lateinit var btnCreateAccount : Button
    private lateinit var inputEmail : TextInputLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCreateAccount = view.findViewById(R.id.btn_newAccountFragment_createAccount)
        inputEmail = view.findViewById(R.id.inputLayout_newAccountFragment_email)

        setListeners()
    }

    private fun setListeners() {
        btnCreateAccount.setOnClickListener {
            val email = inputEmail.editText!!.text.toString().trim();
            if(email != "" && email.contains('@')){
                (activity as MainActivity).addValidEmail(email)

                requireView().findNavController().navigate(
                    NewAccountFragmentDirections.actionNewAccountFragmentToHomeFragment(
                        email
                    )
                )

            }else{
                val toastMsg = Toast.makeText(activity, "Debe ingresar un email válido.", Toast.LENGTH_SHORT)
                toastMsg.show()
            }
        }
    }
}