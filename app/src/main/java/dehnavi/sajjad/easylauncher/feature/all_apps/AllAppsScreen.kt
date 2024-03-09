package dehnavi.sajjad.easylauncher.feature.all_apps

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


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
        LazyColumn {
            items(uiState.allAppsList) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors()
                        .copy(containerColor = MaterialTheme.colorScheme.background),
                    onClick = {
                        val launchIntent: Intent? =
                            context.packageManager.getLaunchIntentForPackage(it.packageName)
                        if (launchIntent != null) {
                            context.startActivity(launchIntent) //null pointer check in case package name was not found
                        }
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 8.dp),
                        text = it.name,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
        }
    }
}