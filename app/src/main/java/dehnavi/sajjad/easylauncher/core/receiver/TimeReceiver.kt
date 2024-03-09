package dehnavi.sajjad.easylauncher.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dehnavi.sajjad.easylauncher.core.data.repository.LocalRepository
import dehnavi.sajjad.easylauncher.core.utils.dispacher.di.ApplicationScope
import dehnavi.sajjad.easylauncher.domain.receiver.use_case.UpdateDateTimeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

class TimeReceiver @Inject constructor(
    private val updateDateTimeUseCase: UpdateDateTimeUseCase,
    @ApplicationScope private val appScope: CoroutineScope,
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when (intent.action) {
                Intent.ACTION_TIMEZONE_CHANGED,
                Intent.ACTION_TIME_TICK,
                Intent.ACTION_TIME_CHANGED -> {
                    val localDataTime = LocalDateTime.now()
                    appScope.launch {
                        updateDateTimeUseCase.invoke(
                            hour = localDataTime.hour,
                            minute = localDataTime.minute,
                            dayOfMonth = localDataTime.dayOfMonth,
                            dayOfWeekName = localDataTime.dayOfWeek.name,
                            year = localDataTime.year,
                            monthName = localDataTime.month.name
                        )
                    }
                }

                else -> {}
            }
        }
    }
}