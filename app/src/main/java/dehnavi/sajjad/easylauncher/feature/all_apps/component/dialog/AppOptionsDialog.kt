package dehnavi.sajjad.easylauncher.feature.all_apps.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dehnavi.sajjad.easylauncher.R
import dehnavi.sajjad.easylauncher.core.model.AppPackage

@Composable
fun AppOptionsDialog(
    appPackage: AppPackage,
    onFavoriteClick: () -> Unit,
    onAppInfoClick: () -> Unit,
    onUninstallClick: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = appPackage.name) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy((4.dp))
            ) {
                ItemOptionsApp(
                    onClick = onFavoriteClick,
                    text = if (!appPackage.isFavorite) {
                        stringResource(R.string.add_to_favorite)
                    } else {
                        stringResource(R.string.remove_from_favorite)
                    }
                )
                ItemOptionsApp(
                    onClick = onAppInfoClick,
                    text = stringResource(R.string.app_info)
                )
                ItemOptionsApp(
                    onClick = onUninstallClick,
                    text = stringResource(R.string.uninstall)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
    )
}

@Composable
private fun ItemOptionsApp(onClick: () -> Unit, text: String) {
    TextButton(
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

    }
}