package dehnavi.sajjad.easylauncher.domain.home.use_case

import dehnavi.sajjad.easylauncher.core.data.repository.DatabaseRepository
import dehnavi.sajjad.easylauncher.core.model.AppPackage
import javax.inject.Inject

class GetAllFavoriteAppPackageListUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke() =
        databaseRepository.getAllFavoritePackage()
}