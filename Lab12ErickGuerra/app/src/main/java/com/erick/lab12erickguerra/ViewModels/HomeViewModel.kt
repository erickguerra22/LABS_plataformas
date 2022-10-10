package com.erick.lab12erickguerra.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<HomeFragmentState> =
        MutableStateFlow(HomeFragmentState.Default("Selecciona una opción"))
    val uiState: StateFlow<HomeFragmentState> = _uiState

    sealed class HomeFragmentState{
        object Loading: HomeFragmentState()
        class Default(val message: String): HomeFragmentState()
        class Success(val message: String): HomeFragmentState()
        class Failure(val message: String): HomeFragmentState()
        class Empty(val message: String): HomeFragmentState()
    }

    fun doSuccess(){
        viewModelScope.launch{
            _uiState.value = HomeFragmentState.Loading
            delay(2000L)
            _uiState.value = HomeFragmentState.Success("¡Operación exitosa!")
        }
    }

    fun doFailure(){
        viewModelScope.launch{
            _uiState.value = HomeFragmentState.Loading
            delay(2000L)
            _uiState.value = HomeFragmentState.Failure("¡Operación fallida!")
        }
    }

    fun doEmpty(){
        viewModelScope.launch{
            _uiState.value = HomeFragmentState.Loading
            delay(2000L)
            _uiState.value = HomeFragmentState.Empty("Sin resultados")
        }
    }

    fun doDefault(){
        viewModelScope.launch{
            _uiState.value = HomeFragmentState.Loading
            delay(2000L)
            _uiState.value = HomeFragmentState.Default("Selecciona una opción")
        }
    }
}