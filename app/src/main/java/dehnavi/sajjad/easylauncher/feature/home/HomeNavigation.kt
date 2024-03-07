package dehnavi.sajjad.easylauncher.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home_route"

fun NavGraphBuilder.homeScreen() {
    composable(HOME_ROUTE) {
        HomeRoute()
    }
}