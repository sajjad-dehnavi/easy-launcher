package dehnavi.sajjad.easylauncher.core.utils.extension

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Context.TELECOM_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.telecom.TelecomManager
import androidx.core.content.ContextCompat


fun PackageManager.launcherAppFromPackageName(context: Context, packageName: String) {
    val launchIntent: Intent? = getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        context.startActivity(launchIntent)
    }
}

fun PackageManager.getDialerAppPackageName(context: Context): String {
    val manger: TelecomManager = context.getSystemService(TELECOM_SERVICE) as TelecomManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        manger.getDefaultDialerPackage()
    } else {
        val dialingIntent = Intent(Intent.ACTION_DIAL).addCategory(Intent.CATEGORY_DEFAULT)
        val resolveInfoList: List<ResolveInfo> = queryIntentActivities(dialingIntent, 0)
        resolveInfoList[0].activityInfo.packageName
    }
}

fun PackageManager.getCameraAppPackageName(): String {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    return intent.resolveActivity(this).packageName
}

fun Context.startApplicationDetailsActivity(packageName: String) {
    try {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.setData(Uri.parse("package:$packageName"))
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        val intent = Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
        startActivity(intent)
    }
}

fun Context.uninstallApp(packageName: String) {
    val packageURI = Uri.parse("package:$packageName")
    val uninstallIntent = Intent(Intent.ACTION_DELETE, packageURI)
    startActivity(uninstallIntent)
}