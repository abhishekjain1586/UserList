package com.anz.userlist.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.anz.presentation.route.UserDetailRoute
import com.anz.presentation.route.UserListRoute
import com.anz.presentation.route.userDetailDestination
import com.anz.presentation.route.userListDestination

@Composable
fun UserAppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = UserListRoute
    ) {
        userListDestination(
            navigateToUserDetail = { userId ->
                navController.navigate(UserDetailRoute(userId = userId))
            }
        )

        userDetailDestination(
            onBack = { navController.navigateUp() }
        )
    }
}