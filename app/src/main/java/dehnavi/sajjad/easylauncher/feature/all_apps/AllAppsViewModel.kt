package dehnavi.sajjad.easylauncher.feature.all_apps

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.easylauncher.core.model.AppPackage
import dehnavi.sajjad.easylauncher.domain.all_apps.use_case.GetAllAppsFromDBUseCase
import dehnavi.sajjad.easylauncher.domain.all_apps.use_case.AddAppToFavoriteUseCase
import dehnavi.sajjad.easylauncher.domain.all_apps.use_case.RemoveAppFromFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAppsViewModel @Inject constructor(
    private val getAllAppsFromDBUseCase: GetAllAppsFromDBUseCase,
    private val addAppToFavoriteUseCase: AddAppToFavoriteUseCase,
    private val removeAppFromFavoriteUseCase: RemoveAppFromFavoriteUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<AllAppsUIState> = MutableStateFlow(AllAppsUIState())
    val uiState
        get() = _uiState.asStateFlow()

    init {
        collectAllApps()
    }

    private fun collectAllApps() = viewModelScope.launch {
        getAllAppsFromDBUseCase.invoke().collectLatest { allAppList ->
            _uiState.update { it.copy(allAppsList = allAppList.toMutableStateList()) }
        }
    }

    fun showOptionsAppDialog() {
        _uiState.update { it.copy(isShowOptionsAppDialog = true) }
    }

    fun hideOptionsAppDialog() {
        _uiState.update { it.copy(isShowOptionsAppDialog = false) }
    }

    fun setSelectedAppPackage(appPackage: AppPackage) {
        _uiState.update { it.copy(selectedAppPackage = appPackage) }
    }

    fun addToFavoriteAppPackage(appPackage: AppPackage) = viewModelScope.launch {
        addAppToFavoriteUseCase.invoke(appPackage)
    }

    fun removeFromFavoriteAppPackage(appPackage: AppPackage) = viewModelScope.launch {
        removeAppFromFavoriteUseCase.invoke(appPackage)
    }
}