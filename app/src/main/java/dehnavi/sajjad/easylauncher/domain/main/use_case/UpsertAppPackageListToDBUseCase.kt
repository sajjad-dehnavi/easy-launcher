package dehnavi.sajjad.easylauncher.domain.main.use_case

import dehnavi.sajjad.easylauncher.core.data.repository.DatabaseRepository
import dehnavi.sajjad.easylauncher.core.model.AppPackage
import javax.inject.Inject

class UpsertAppPackageListToDBUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(appPackageList: List<AppPackage>) =
        databaseRepository.upsertAppPackageList(appPackageList)
}