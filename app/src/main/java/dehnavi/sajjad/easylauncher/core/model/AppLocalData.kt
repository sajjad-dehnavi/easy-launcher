package dehnavi.sajjad.easylauncher.core.model

import kotlinx.serialization.Serializable

@Serializable
data class AppLocalData(
    val batteryCharge: Int = 0,
    val hour: Int = 0,
    val minute: Int = 0,
    val dayOfMonth: Int = 0,
    val dayOfWeekName: String = "",
    val monthName: String = "",
    val year: Int = 0,
) {
    fun concatTime(): String {
        val strHour = if (hour in 0..9) {
            "0$hour"
        } else {
            hour.toShort()
        }
        val strMinute = if (minute in 0..9) {
            "0$minute"
        } else {
            minute.toShort()
        }

        return "$strHour:$strMinute"
    }

    fun concatDate() =
        "$dayOfWeekName, $monthName $dayOfMonth"
}

