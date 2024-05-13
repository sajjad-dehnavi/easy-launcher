package dehnavi.sajjad.easylauncher.feature.all_apps

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import dehnavi.sajjad.easylauncher.core.model.AppPackage

data class AllAppsUIState(
    val allAppsList: SnapshotStateList<AppPackage> = mutableStateListOf(),
    val isShowOptionsAppDialog: Boolean = false,
    val selectedAppPackage: AppPackage? = null,
)
