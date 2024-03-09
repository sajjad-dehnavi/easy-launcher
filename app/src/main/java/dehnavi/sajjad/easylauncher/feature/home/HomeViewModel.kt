package dehnavi.sajjad.easylauncher.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.easylauncher.core.data.repository.LocalRepository
import dehnavi.sajjad.easylauncher.domain.home.use_case.GetAppLocalDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAppLocalDataUseCase: GetAppLocalDataUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState
        get() = _uiState.asStateFlow()

    init {
        collectAppLocalData()
    }

    private fun collectAppLocalData() {
        viewModelScope.launch {
            getAppLocalDataUseCase.invoke().collectLatest { data ->
                _uiState.update { it.copy(appLocalData = data) }
            }
        }
    }
}