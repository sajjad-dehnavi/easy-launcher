package dehnavi.sajjad.easylauncher.feature.home

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.easylauncher.domain.home.use_case.GetAllFavoriteAppPackageListUseCase
import dehnavi.sajjad.easylauncher.domain.home.use_case.GetAppLocalDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAppLocalDataUseCase: GetAppLocalDataUseCase,
    private val getAllFavoriteAppPackageListUseCase: GetAllFavoriteAppPackageListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState
        get() = _uiState.asStateFlow()

    init {
        collectAppLocalData()
        collectFavoriteAppPackageList()
    }

    private fun collectFavoriteAppPackageList() = viewModelScope.launch {
        getAllFavoriteAppPackageListUseCase.invoke().collectLatest { list ->
            Log.d("TAG", "collectFavoriteAppPackageList: ${list.size}")
            _uiState.update { it.copy(favoriteAppsList = list.toMutableStateList()) }
        }
    }

    private fun collectAppLocalData() {
        viewModelScope.launch {
            getAppLocalDataUseCase.invoke().collectLatest { data ->
                _uiState.update { it.copy(appLocalData = data) }
            }
        }
    }
}