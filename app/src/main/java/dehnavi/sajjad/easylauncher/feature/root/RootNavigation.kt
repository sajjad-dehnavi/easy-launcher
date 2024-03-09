package dehnavi.sajjad.easylauncher.feature.root

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val ROOT_ROUTE = "root_route"

fun NavGraphBuilder.rootScreen() {
    composable(ROOT_ROUTE) {
        BackHandler(true) {
        }
        RootRoute()
    }
}