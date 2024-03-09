package dehnavi.sajjad.easylauncher.core.model

import kotlinx.serialization.Serializable

@Serializable
data class AppPackage(
    val packageName: String = "",
    val name:String="",
    val isFavorite:Boolean=false,
)