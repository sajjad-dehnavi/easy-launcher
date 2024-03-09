package dehnavi.sajjad.easylauncher.feature.all_apps

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val All_APPS_ROUTE = "all_apps_route"

fun NavGraphBuilder.allAppsScreen() {
    composable(All_APPS_ROUTE) {
        AllAppsRoute()
    }
}