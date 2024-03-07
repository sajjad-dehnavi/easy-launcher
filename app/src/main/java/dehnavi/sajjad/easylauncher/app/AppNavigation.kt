package dehnavi.sajjad.easylauncher.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dehnavi.sajjad.easylauncher.feature.home.HOME_ROUTE
import dehnavi.sajjad.easylauncher.feature.home.homeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier=modifier,
        navController = navController,
        startDestination = HOME_ROUTE
    ) {
        homeScreen()
    }
}