package dehnavi.sajjad.easylauncher.core.data.repository

import dehnavi.sajjad.easylauncher.core.datastore.AppLocalDataPreferences
import dehnavi.sajjad.easylauncher.core.utils.dispacher.AppDispatchers
import dehnavi.sajjad.easylauncher.core.utils.dispacher.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val appDataStore: AppLocalDataPreferences,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {
    fun getAppLocalData() = appDataStore.appLocalData


    suspend fun updateCurrentTime(
        hour: Int,
        minute: Int,
        dayOfMonth: Int,
        dayOfWeekName: String,
        monthName: String,
        year: Int,
    ) {
        appDataStore.updateCurrentTime(
            hour = hour,
            minute = minute,
            dayOfMonth = dayOfMonth,
            dayOfWeekName = dayOfWeekName,
            monthName = monthName,
            year = year,
        )
    }

    suspend fun updateBatteryCharge(charge: Int) = withContext(ioDispatcher) {
        appDataStore.updateBatteryCharge(charge)
    }
}