package dehnavi.sajjad.easylauncher.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dehnavi.sajjad.easylauncher.core.model.AppPackage
import dehnavi.sajjad.easylauncher.core.utils.dispacher.di.ApplicationScope
import dehnavi.sajjad.easylauncher.domain.main.use_case.InsertAppPackageListToDBUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PackageAddedReceiver @Inject constructor(
    private val insertPackageUseCase: InsertAppPackageListToDBUseCase,
    @ApplicationScope private val appScope: CoroutineScope,
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when (intent.action) {
                Intent.ACTION_PACKAGE_ADDED,
                Intent.ACTION_PACKAGE_INSTALL -> {
                    val packageName = intent.data?.encodedSchemeSpecificPart
                    appScope.launch {
                        packageName?.let {

                            val name = context?.packageManager?.getPackageInfo(
                                packageName,
                                0
                            )?.applicationInfo?.loadLabel(context.packageManager!!).toString()
                            insertPackageUseCase.invoke(
                                listOf(
                                    AppPackage(
                                        packageName = packageName,
                                        name = name,
                                        isFavorite = false,
                                    )
                                )
                            )
                        }
                    }
                }

                else -> {}
            }
        }
    }
}