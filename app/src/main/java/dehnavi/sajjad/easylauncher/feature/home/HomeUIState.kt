package dehnavi.sajjad.easylauncher.feature.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import dehnavi.sajjad.easylauncher.core.model.AppLocalData
import dehnavi.sajjad.easylauncher.core.model.AppPackage

data class HomeUIState(
    val favoriteAppsList: SnapshotStateList<AppPackage> = mutableStateListOf(),
    val appLocalData: AppLocalData = AppLocalData(),
)
