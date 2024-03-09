package dehnavi.sajjad.easylauncher.feature.all_apps

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.easylauncher.domain.all_apps.use_case.GetAllAppsFromDBUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAppsViewModel @Inject constructor(
    private val getAllAppsFromDBUseCase: GetAllAppsFromDBUseCase,
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
}