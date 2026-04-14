package com.anz.userlist.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.anz.users_presentation.route.UserDetailRoute
import com.anz.users_presentation.route.UserListRoute
import com.anz.users_presentation.route.userDetailDestination
import com.anz.users_presentation.route.userListDestination

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