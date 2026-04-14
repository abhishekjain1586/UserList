package com.anz.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.anz.common.result.Result
import com.anz.domain.model.User
import com.anz.domain.usecase.GetUserDetailUseCase
import com.anz.presentation.route.UserDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val userId = savedStateHandle.toRoute<UserDetailRoute>().userId

    private val _uiState = MutableStateFlow(UserDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UserDetailUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        fetchUserDetail()
    }

    private fun fetchUserDetail() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            when (val result = getUserDetailUseCase(userId = userId)) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            user = result.data
                        )
                    }
                }
                is Result.Error -> {
                    _uiState.update { it.copy(loading = false) }
                    _uiEvent.emit(UserDetailUiEvent.Error(result.errorMessage))
                }
            }
        }
    }

    data class UserDetailUiState(
        val loading: Boolean = true,
        val user: User? = null
    )

    sealed class UserDetailUiEvent {
        data class Error(val errorMessage: String) : UserDetailUiEvent()
    }
}
