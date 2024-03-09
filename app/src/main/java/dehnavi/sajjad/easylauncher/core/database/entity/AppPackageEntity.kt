package dehnavi.sajjad.easylauncher.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "package_entity")
data class AppPackageEntity(
    @PrimaryKey
    @ColumnInfo(name = "package_name")
    val packageName: String = "",
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
    val name: String = "",
)