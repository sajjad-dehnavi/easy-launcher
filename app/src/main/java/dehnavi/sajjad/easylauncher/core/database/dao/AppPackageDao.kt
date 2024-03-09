package dehnavi.sajjad.easylauncher.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import dehnavi.sajjad.easylauncher.core.database.entity.AppPackageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppPackageDao {
    @Upsert
    suspend fun updatePackageEntities(packageList: List<AppPackageEntity>)

    @Insert
    suspend fun insertPackageEntity(appPackageEntity: AppPackageEntity)

    @Query(
        value = """
        SELECT * FROM package_entity ORDER BY name"""
    )
    fun getAllPackageEntities(): Flow<List<AppPackageEntity>>
}