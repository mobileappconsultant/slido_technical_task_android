package com.example.usersdir.ui.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersdir.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

private const val EVENT_DELAY = 200L

@HiltViewModel
class CreateUserViewModel @Inject constructor(private val createUserUseCase: CreateUserUseCase) :
    ViewModel() {

    private val _event = MutableStateFlow<Event>(Event.Clean)
    val event = _event.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _event.value = Event.ShowToast("An error occurred")
        _loadingState.value = false
    }

    fun createUser(
        email: String,
        gender: String,
        name: String,
        status: String
    ) {
        _loadingState.value = true
        viewModelScope.launch(coroutineExceptionHandler) {
            createUserUseCase.execute(email, gender, name, status)
            _loadingState.value = false
            _event.value = Event.ShowToast("User added")
            delay(EVENT_DELAY)
            _event.value = Event.Navigate
        }
    }

    fun clean() {
        _event.value = Event.Clean
    }

    sealed class Event {
        data class ShowToast(val message: String) : Event()
        object Navigate : Event()
        object Clean : Event()
    }
}
