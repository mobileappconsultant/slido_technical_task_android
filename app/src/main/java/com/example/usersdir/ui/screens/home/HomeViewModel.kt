package com.example.usersdir.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersdir.domain.model.User
import com.example.usersdir.domain.usecase.DeleteUserUseCase
import com.example.usersdir.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<DataState?>(null)
    val uiState = _uiState.asStateFlow()

    private val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _uiState.value = DataState.Failure(throwable.message ?: "An error occurred")
    }

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = DataState.Loading
            getUsersUseCase.execute().collect {
                _uiState.value = DataState.Success(it)
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            deleteUserUseCase.execute(user.id)
        }
    }

    sealed interface DataState {
        data class Success(val users: List<User>) : DataState
        object Loading : DataState
        data class Failure(val message: String) : DataState
    }
}
