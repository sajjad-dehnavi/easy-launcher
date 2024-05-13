package dehnavi.sajjad.easylauncher.feature.all_apps

import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dehnavi.sajjad.easylauncher.core.model.AppPackage
import dehnavi.sajjad.easylauncher.core.utils.extension.launcherAppFromPackageName
import dehnavi.sajjad.easylauncher.core.utils.extension.startApplicationDetailsActivity
import dehnavi.sajjad.easylauncher.core.utils.extension.uninstallApp
import dehnavi.sajjad.easylauncher.feature.all_apps.component.dialog.AppOptionsDialog


@Composable
fun AllAppsRoute(
    viewModel: AllAppsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AllAppsScreen(
        uiState = uiState,
        showOptionsAppDialog = viewModel::showOptionsAppDialog,
        hideOptionsAppDialog = viewModel::hideOptionsAppDialog,
        setSelectAppPackage = viewModel::setSelectedAppPackage,
        addAppToFavorite = viewModel::addToFavoriteAppPackage,
        removeAppFromFavorite = viewModel::removeFromFavoriteAppPackage,
    )
}

@Composable
private fun AllAppsScreen(
    uiState: AllAppsUIState,
    showOptionsAppDialog: () -> Unit,
    hideOptionsAppDialog: () -> Unit,
    setSelectAppPackage: (AppPackage) -> Unit,
    addAppToFavorite: (AppPackage) -> Unit,
    removeAppFromFavorite: (AppPackage) -> Unit,
) {
    val context = LocalContext.current

    if (uiState.isShowOptionsAppDialog) {
        uiState.selectedAppPackage?.let {
            AppOptionsDialog(
                appPackage = it,
                onFavoriteClick = {
                    if (it.isFavorite.not()) {
                        addAppToFavorite(it)
                    } else {
                        removeAppFromFavorite(it)
                    }
                    hideOptionsAppDialog()
                },
                onAppInfoClick = {
                    context.startApplicationDetailsActivity(it.packageName)
                    hideOptionsAppDialog()
                },
                onUninstallClick = {
                    context.uninstallApp(it.packageName)
                    hideOptionsAppDialog()
                },
                onDismiss = hideOptionsAppDialog,
            )
        }
    }

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
                        context = context,
                        showOptionsAppDialog = showOptionsAppDialog,
                        setSelectAppPackage = setSelectAppPackage,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AllAppsListComponent(
    uiState: AllAppsUIState,
    context: Context,
    showOptionsAppDialog: () -> Unit,
    setSelectAppPackage: (AppPackage) -> Unit,
) {
    LazyColumn {
        items(uiState.allAppsList) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .combinedClickable(
                        onClick = {
                            context.packageManager.launcherAppFromPackageName(
                                context = context,
                                packageName = it.packageName
                            )
                        },
                        onLongClick = {
                            setSelectAppPackage(it)
                            showOptionsAppDialog()
                        }
                    ),
                colors = CardDefaults.cardColors()
                    .copy(containerColor = MaterialTheme.colorScheme.background),
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