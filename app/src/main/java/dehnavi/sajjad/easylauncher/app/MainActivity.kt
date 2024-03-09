package dehnavi.sajjad.easylauncher.app

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ResolveInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dehnavi.sajjad.easylauncher.core.model.AppPackage
import dehnavi.sajjad.easylauncher.core.receiver.BatteryReceiver
import dehnavi.sajjad.easylauncher.core.receiver.TimeReceiver
import dehnavi.sajjad.easylauncher.ui.theme.EasyLauncherTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var batteryReceiver: BatteryReceiver

    @Inject
    lateinit var timeReceiver: TimeReceiver

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAllAppsFromPackageManager()

        setContent {
            val navController = rememberNavController()
            EasyLauncherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AppNavHost(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    private fun getAllAppsFromPackageManager() {
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList: List<ResolveInfo> =
            packageManager.queryIntentActivities(mainIntent, 0)
        pkgAppsList.map {
            val packageInfo = packageManager.getPackageInfo(it.activityInfo.packageName, 0)
            AppPackage(
                name = packageInfo.applicationInfo.loadLabel(packageManager).toString(),
                packageName = it.activityInfo.packageName
            )
        }.also { appList ->
            viewModel.upsertAppPackageList(appList)
        }
    }

    override fun onStart() {
        super.onStart()
        val batteryIntentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, batteryIntentFilter)

        val timeIntentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_TICK)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
        }
        registerReceiver(timeReceiver, timeIntentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryReceiver)
        unregisterReceiver(timeReceiver)
    }
}