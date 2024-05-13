package dehnavi.sajjad.easylauncher.core.data.repository

import dehnavi.sajjad.easylauncher.core.data.mapper.toEntity
import dehnavi.sajjad.easylauncher.core.data.mapper.toModel
import dehnavi.sajjad.easylauncher.core.database.dao.AppPackageDao
import dehnavi.sajjad.easylauncher.core.model.AppPackage
import dehnavi.sajjad.easylauncher.core.utils.dispacher.AppDispatchers
import dehnavi.sajjad.easylauncher.core.utils.dispacher.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val appPackageDao: AppPackageDao,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun insertAppPackageList(list: List<AppPackage>) = withContext(ioDispatcher) {
        appPackageDao.insertPackageEntities(list.map { it.toEntity() })
    }

    suspend fun updateAppPackage(appPackage: AppPackage) = withContext(ioDispatcher) {
        appPackageDao.updatePackageEntity(appPackage.toEntity())
    }

    suspend fun getAllPackage() = withContext(ioDispatcher) {
        appPackageDao.getAllPackageEntities().map { list -> list.map { it.toModel() } }
    }

    suspend fun getAllFavoritePackage() = withContext(ioDispatcher) {
        appPackageDao.getAllPackageFavouritesEntities().map { list -> list.map { it.toModel() } }
    }

    suspend fun deleteAppPackage(packageName: String) = withContext(ioDispatcher) {
        appPackageDao.deletePackageEntity(packageName)
    }
}