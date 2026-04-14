package com.anz.users_presentation.route

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anz.users_presentation.ui.detail.UserDetailScreen
import com.anz.users_presentation.ui.detail.UserDetailViewModel
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailRoute(
    val userId: Int
)

fun NavGraphBuilder.userDetailDestination(
    onBack: () -> Unit
) {
    composable<UserDetailRoute> {
        val viewModel: UserDetailViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        UserDetailScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onBack = onBack
        )
    }
}