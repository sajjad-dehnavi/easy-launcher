package dehnavi.sajjad.easylauncher.domain.receiver.use_case

import dehnavi.sajjad.easylauncher.core.repository.LocalRepository
import javax.inject.Inject

class UpdateBatteryChargeUseCase @Inject constructor(
    private val localRepository: LocalRepository,
) {
    suspend operator fun invoke(charge: Int) =
        localRepository.updateBatteryCharge(charge)
}