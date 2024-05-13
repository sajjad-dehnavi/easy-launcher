package dehnavi.sajjad.easylauncher.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dehnavi.sajjad.easylauncher.R
import dehnavi.sajjad.easylauncher.core.utils.extension.getCameraAppPackageName
import dehnavi.sajjad.easylauncher.core.utils.extension.getDialerAppPackageName
import dehnavi.sajjad.easylauncher.core.utils.extension.launcherAppFromPackageName
import dehnavi.sajjad.easylauncher.feature.home.component.BatteryAndTimeComponent

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUIState,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
    ) {
        BatteryAndTimeComponent(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp),
            appLocalData = uiState.appLocalData
        )

        LazyColumn(
            modifier = Modifier.align(Alignment.Center),
        ) {
            items(uiState.favoriteAppsList) {
                Text(text = it.name)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {
                val packageNameDialer = context.packageManager.getDialerAppPackageName(context)
                context.packageManager.launcherAppFromPackageName(
                    context = context,
                    packageName = packageNameDialer
                )
            }) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "call",
                )
            }

            IconButton(onClick = {
                val packageNameCamera = context.packageManager.getCameraAppPackageName()
                context.packageManager.launcherAppFromPackageName(
                    context = context,
                    packageName = packageNameCamera
                )
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.photo_camera),
                    contentDescription = "camera",
                )
            }
        }
    }
}