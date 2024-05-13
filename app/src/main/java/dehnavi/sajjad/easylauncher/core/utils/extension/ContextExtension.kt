package dehnavi.sajjad.easylauncher.core.utils.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper


fun Context?.getActivity(): Activity? {
    if (this == null) {
        return null
    } else if (this is ContextWrapper) {
        return if (this is Activity) {
            this
        } else {
            this.baseContext.getActivity()
        }
    }

    return null
}