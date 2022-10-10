package com.erick.lab12erickguerra.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.erick.lab12erickguerra.R
import com.erick.lab12erickguerra.ViewModels.SessionViewModel
import com.erick.lab12erickguerra.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class LoginFragment : Fragment(){

    private lateinit var binding: FragmentLoginBinding
    private val sessionViewModel: SessionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        binding.btnLoginLoginFragment.setOnClickListener{
            binding.btnLoginLoginFragment.isVisible = false
            binding.progressBarLoginFragment.isVisible = true

            lifecycleScope.launch {
                delay(2000L)
                if(sessionViewModel.validateAuthToken(
                        requireContext(),
                        binding.inputTextMailLoginFragment.editText!!.text.toString(),
                        binding.inputTextPasswordLoginFragment.editText!!.text.toString()
                    )){
                    requireView().findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }
                else{
                    binding.progressBarLoginFragment.isVisible = false
                    binding.btnLoginLoginFragment.isVisible = true
                    Toast.makeText(activity, "Error: Las credenciales no coinciden o no son válidas", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}