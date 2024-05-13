package dehnavi.sajjad.easylauncher.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehnavi.sajjad.easylauncher.core.model.AppPackage
import dehnavi.sajjad.easylauncher.domain.main.use_case.InsertAppPackageListToDBUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insertAppPackageListToDBUseCase: InsertAppPackageListToDBUseCase
) : ViewModel() {
    fun upsertAppPackageList(appPackageList: List<AppPackage>) = viewModelScope.launch {
        insertAppPackageListToDBUseCase.invoke(appPackageList)
    }
}