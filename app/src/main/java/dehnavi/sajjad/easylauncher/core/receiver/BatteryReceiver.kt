package dehnavi.sajjad.easylauncher.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import dehnavi.sajjad.easylauncher.core.repository.LocalRepository
import dehnavi.sajjad.easylauncher.core.utils.dispacher.di.ApplicationScope
import dehnavi.sajjad.easylauncher.domain.receiver.use_case.UpdateBatteryChargeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class BatteryReceiver @Inject constructor(
    private val updateBatteryChargeUseCase: UpdateBatteryChargeUseCase,
    @ApplicationScope private val appScope: CoroutineScope,
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when (intent.action) {
                Intent.ACTION_BATTERY_CHANGED -> {
                    val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                    val charge: Int = (level * 100 / scale.toFloat()).toInt()
                    appScope.launch {
                        updateBatteryChargeUseCase.invoke(charge)
                    }
                }

                else -> {}
            }
        }
    }
}