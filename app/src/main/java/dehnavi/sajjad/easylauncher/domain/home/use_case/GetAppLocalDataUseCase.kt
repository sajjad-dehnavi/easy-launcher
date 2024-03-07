package dehnavi.sajjad.easylauncher.domain.home.use_case

import dehnavi.sajjad.easylauncher.core.repository.LocalRepository
import javax.inject.Inject

class GetAppLocalDataUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    operator fun invoke() = localRepository.getAppLocalData()
}