package dehnavi.sajjad.easylauncher.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dehnavi.sajjad.easylauncher.core.database.entity.AppPackageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppPackageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPackageEntities(packageList: List<AppPackageEntity>)

    @Update
    suspend fun updatePackageEntity(appPackageEntity: AppPackageEntity)

    @Query(
        """
            DELETE FROM package_entity WHERE package_name = :packageName
        """
    )
    fun deletePackageEntity(packageName: String)

    @Query(
        value = """
        SELECT * FROM package_entity ORDER BY name"""
    )
    fun getAllPackageEntities(): Flow<List<AppPackageEntity>>

    @Query(
        value = """
        SELECT * FROM package_entity WHERE is_favorite = 1 ORDER BY name"""
    )
    fun getAllPackageFavouritesEntities(): Flow<List<AppPackageEntity>>
}