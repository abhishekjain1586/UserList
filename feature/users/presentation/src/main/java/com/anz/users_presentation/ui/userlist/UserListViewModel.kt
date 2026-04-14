package com.anz.users_presentation.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anz.common.result.Result
import com.anz.users_domain.model.User
import com.anz.users_domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UserListUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            when (val result = getUsersUseCase()) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            users = result.data.toPersistentList()
                        )
                    }
                }
                is Result.Error -> {
                    _uiState.update { it.copy(loading = false) }
                    _uiEvent.emit(UserListUiEvent.Error(result.errorMessage))
                }
            }
        }
    }

    fun onItemClick(userId: Int) {
        viewModelScope.launch {
            _uiEvent.emit(UserListUiEvent.UserDetailNavigation(userId = userId))
        }
    }

    data class UserListUiState(
        val loading: Boolean = true,
        val users: ImmutableList<User> = persistentListOf()
    )

    sealed class UserListUiEvent {
        data class Error(val errorMessage: String) : UserListUiEvent()
        data class UserDetailNavigation(val userId: Int) : UserListUiEvent()
    }
}
