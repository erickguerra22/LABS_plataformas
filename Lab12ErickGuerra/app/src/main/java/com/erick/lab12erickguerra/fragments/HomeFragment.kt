package com.erick.lab12erickguerra.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.erick.lab12erickguerra.R
import com.erick.lab12erickguerra.ViewModels.HomeViewModel
import com.erick.lab12erickguerra.ViewModels.SessionViewModel
import com.erick.lab12erickguerra.databinding.FragmentHomeBinding
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding:FragmentHomeBinding
    private val sessionViewModel: SessionViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservables()
        setListeners()
    }

    private fun setListeners() {
        binding.btnKeepSessionHomeFragment.setOnClickListener {
            sessionViewModel.keepSession()
            Toast.makeText(activity, "La sesión ya no se cerrará automáticamente", Toast.LENGTH_LONG).show()
        }
        binding.btnLogoutHomeFragment.setOnClickListener {
            sessionViewModel.logOut()
            Toast.makeText(activity, "Ha cerrado sesión", Toast.LENGTH_LONG).show()
        }
        binding.btnDefaultStateHomeFragment.setOnClickListener {
            homeViewModel.doDefault()
        }
        binding.btnSuccessStateHomeFragment.setOnClickListener {
            homeViewModel.doSuccess()
        }
        binding.btnFailureStateHomeFragment.setOnClickListener {
            homeViewModel.doFailure()
        }
        binding.btnEmptyStateHomeFragment.setOnClickListener {
            homeViewModel.doEmpty()
        }
    }

    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            sessionViewModel.validAuthToken.collectLatest { activeSession ->
                if(!activeSession)
                    requireView().findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            }
        }
        lifecycleScope.launchWhenStarted {
            homeViewModel.uiState.collectLatest {state->
                handleState(state)
            }
        }
    }

    private fun handleState(state: HomeViewModel.HomeFragmentState) {
        binding.progressBarHomeFragment.isVisible = false
        binding.txtStateMessageHomeFragment.isVisible = true
        binding.iconStateIconHomeFragment.isVisible = true
        binding.btnDefaultStateHomeFragment.isEnabled = true
        binding.btnEmptyStateHomeFragment.isEnabled = true
        binding.btnFailureStateHomeFragment.isEnabled = true
        binding.btnSuccessStateHomeFragment.isEnabled = true
        when(state){
            is HomeViewModel.HomeFragmentState.Default -> {
                binding.txtStateMessageHomeFragment.text = state.message
                binding.iconStateIconHomeFragment.setImageResource(R.drawable.ic_default)
            }
            is HomeViewModel.HomeFragmentState.Success -> {
                binding.txtStateMessageHomeFragment.text = state.message
                binding.iconStateIconHomeFragment.setImageResource(R.drawable.ic_success)
            }
            is HomeViewModel.HomeFragmentState.Failure -> {
                binding.txtStateMessageHomeFragment.text = state.message
                binding.iconStateIconHomeFragment.setImageResource(R.drawable.ic_failure)
            }
            is HomeViewModel.HomeFragmentState.Empty -> {
                binding.txtStateMessageHomeFragment.text = state.message
                binding.iconStateIconHomeFragment.setImageResource(R.drawable.ic_empty)
            }
            is HomeViewModel.HomeFragmentState.Loading -> {
                binding.progressBarHomeFragment.isVisible = true
                binding.txtStateMessageHomeFragment.isVisible = false
                binding.iconStateIconHomeFragment.isVisible = false
                binding.btnDefaultStateHomeFragment.isEnabled = false
                binding.btnEmptyStateHomeFragment.isEnabled = false
                binding.btnFailureStateHomeFragment.isEnabled = false
                binding.btnSuccessStateHomeFragment.isEnabled = false
            }
        }
    }

}