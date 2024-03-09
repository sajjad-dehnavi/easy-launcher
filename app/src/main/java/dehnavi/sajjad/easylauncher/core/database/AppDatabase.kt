package dehnavi.sajjad.easylauncher.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dehnavi.sajjad.easylauncher.core.database.dao.AppPackageDao
import dehnavi.sajjad.easylauncher.core.database.entity.AppPackageEntity

@Database(entities = [AppPackageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appPackageDao(): AppPackageDao
}