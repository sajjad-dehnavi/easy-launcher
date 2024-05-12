package dehnavi.sajjad.easylauncher.core.utils.extension

import android.content.Context
import android.content.Context.TELECOM_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.provider.MediaStore
import android.telecom.TelecomManager
import androidx.core.content.ContextCompat.getSystemService


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