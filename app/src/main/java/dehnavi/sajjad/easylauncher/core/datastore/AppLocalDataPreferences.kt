package dehnavi.sajjad.easylauncher.core.datastore

import androidx.datastore.core.DataStore
import dehnavi.sajjad.easylauncher.core.model.AppLocalData
import javax.inject.Inject

class AppLocalDataPreferences @Inject constructor(
    private val appDataStore: DataStore<AppLocalData>
) {
    val appLocalData
        get() = appDataStore.data

    suspend fun updateCurrentTime(
        hour: Int = 0,
        minute: Int = 0,
        dayOfMonth: Int = 0,
        dayOfWeekName: String = "",
        monthName: String = "",
        year: Int = 0,
    ) {
        appDataStore.updateData {
            it.copy(
                hour = hour,
                minute = minute,
                dayOfMonth = dayOfMonth,
                dayOfWeekName = dayOfWeekName,
                monthName = monthName,
                year = year,
            )
        }
    }

    suspend fun updateBatteryCharge(charge: Int) {
        appDataStore.updateData { it.copy(batteryCharge = charge) }
    }
}