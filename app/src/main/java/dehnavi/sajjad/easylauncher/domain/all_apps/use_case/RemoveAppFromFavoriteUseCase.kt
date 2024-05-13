package dehnavi.sajjad.easylauncher.domain.all_apps.use_case

import dehnavi.sajjad.easylauncher.core.data.repository.DatabaseRepository
import dehnavi.sajjad.easylauncher.core.model.AppPackage
import javax.inject.Inject

class RemoveAppFromFavoriteUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(appPackage: AppPackage) =
        databaseRepository.updateAppPackage(appPackage.copy(isFavorite = false))
}