package dehnavi.sajjad.easylauncher.feature.root

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import dehnavi.sajjad.easylauncher.feature.all_apps.AllAppsRoute
import dehnavi.sajjad.easylauncher.feature.home.HomeRoute

@Composable
fun RootRoute() {
    RootScreen()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RootScreen() {
    val pagerState =
        rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = { 2 },
        )

    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> HomeRoute()
            1 -> AllAppsRoute()
        }
    }
}