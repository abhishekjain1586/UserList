package com.anz.users_presentation.route

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anz.users_presentation.ui.userlist.UserListScreen
import com.anz.users_presentation.ui.userlist.UserListViewModel
import kotlinx.serialization.Serializable

@Serializable
data object UserListRoute

fun NavGraphBuilder.userListDestination(
    navigateToUserDetail: (Int) -> Unit
) {
    composable<UserListRoute> {
        val viewModel: UserListViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        UserListScreen(
            uiState = uiState,
            uiEvent = viewModel.uiEvent,
            onItemClick = viewModel::onItemClick,
            navigateToUserDetail = navigateToUserDetail
        )
    }
}
