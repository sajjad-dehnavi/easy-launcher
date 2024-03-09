package dehnavi.sajjad.easylauncher.domain.all_apps.use_case

import dehnavi.sajjad.easylauncher.core.data.repository.DatabaseRepository
import javax.inject.Inject

class GetAllAppsFromDBUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke() =
        databaseRepository.getAllPackage()
}