package dehnavi.sajjad.easylauncher.domain.main.use_case

import dehnavi.sajjad.easylauncher.core.data.repository.DatabaseRepository
import javax.inject.Inject

class DeleteAppPackageFromDBUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(packageNames: String) =
        databaseRepository.deleteAppPackage(packageNames)
}