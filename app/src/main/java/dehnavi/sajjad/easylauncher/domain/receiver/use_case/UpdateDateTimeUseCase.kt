package dehnavi.sajjad.easylauncher.domain.receiver.use_case

import dehnavi.sajjad.easylauncher.core.repository.LocalRepository
import javax.inject.Inject

class UpdateDateTimeUseCase @Inject constructor(
    private val localRepository: LocalRepository,
) {
    suspend operator fun invoke(
        hour: Int,
        minute: Int,
        dayOfMonth: Int,
        dayOfWeekName: String,
        monthName: String,
        year: Int,
    ) = localRepository.updateCurrentTime(
        hour = hour,
        minute = minute,
        dayOfMonth = dayOfMonth,
        dayOfWeekName = dayOfWeekName,
        monthName = monthName,
        year = year,
    )
}