package com.erick.lab12erickguerra.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erick.lab12erickguerra.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SessionViewModel : ViewModel() {
    private val _validAuthToken: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val validAuthToken: StateFlow<Boolean> = _validAuthToken
    private lateinit var sessionJob:Job

    fun validateAuthToken(context: Context, email:String, password:String): Boolean{
        return if(email == password && email == context.getString(R.string.valid_mail)){
            _validAuthToken.value = true
            sessionJob = viewModelScope.launch {
                delay(30000L)
                _validAuthToken.value = false
            }
            true
        }else
            false
    }

    fun keepSession(){
        if(this::sessionJob.isInitialized && sessionJob.isActive)
            sessionJob.cancel()
        _validAuthToken.value = true
    }

    fun logOut(){
        if(this::sessionJob.isInitialized && sessionJob.isActive)
            sessionJob.cancel()
        _validAuthToken.value = false
    }
}