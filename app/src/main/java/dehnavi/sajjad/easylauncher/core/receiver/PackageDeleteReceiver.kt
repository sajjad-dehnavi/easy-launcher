package dehnavi.sajjad.easylauncher.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import dehnavi.sajjad.easylauncher.core.utils.dispacher.di.ApplicationScope
import dehnavi.sajjad.easylauncher.domain.main.use_case.DeleteAppPackageFromDBUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PackageDeleteReceiver @Inject constructor(
    private val deletePackageUseCase: DeleteAppPackageFromDBUseCase,
    @ApplicationScope private val appScope: CoroutineScope,
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when (intent.action) {
                Intent.ACTION_PACKAGE_REMOVED -> {
                    val packageName = intent.data?.encodedSchemeSpecificPart
                    appScope.launch {
                        packageName?.let {
                            deletePackageUseCase.invoke(packageName)
                        }
                    }
                }

                else -> {}
            }
        }
    }
}