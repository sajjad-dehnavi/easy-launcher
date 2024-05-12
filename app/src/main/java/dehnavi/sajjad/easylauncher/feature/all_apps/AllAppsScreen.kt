package dehnavi.sajjad.easylauncher.feature.all_apps

import android.content.Context
import android.content.Intent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dehnavi.sajjad.easylauncher.core.utils.extension.launcherAppFromPackageName


@Composable
fun AllAppsRoute(
    viewModel: AllAppsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AllAppsScreen(uiState = uiState)
}

@Composable
private fun AllAppsScreen(uiState: AllAppsUIState) {
    val context = LocalContext.current
    Column {
        Crossfade(targetState = uiState.allAppsList.isEmpty(), label = "all_app_list") {
            when (it) {
                true -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                false -> {
                    AllAppsListComponent(
                        uiState = uiState,
                        context = context
                    )
                }
            }
        }
    }
}

@Composable
private fun AllAppsListComponent(
    uiState: AllAppsUIState,
    context: Context
) {
    LazyColumn {
        items(uiState.allAppsList) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = CardDefaults.cardColors()
                    .copy(containerColor = MaterialTheme.colorScheme.background),
                onClick = {
                    context.packageManager.launcherAppFromPackageName(
                        context = context,
                        packageName = it.packageName
                    )
                }
            ) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 6.dp,
                        vertical = 8.dp
                    ),
                    text = it.name,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}