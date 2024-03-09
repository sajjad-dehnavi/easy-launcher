package dehnavi.sajjad.easylauncher.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dehnavi.sajjad.easylauncher.feature.root.ROOT_ROUTE
import dehnavi.sajjad.easylauncher.feature.root.rootScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ROOT_ROUTE,
    ) {
        rootScreen()
    }
}