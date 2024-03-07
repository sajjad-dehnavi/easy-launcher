package dehnavi.sajjad.easylauncher.core.utils.dispacher

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val appDispatchers: AppDispatchers)
enum class AppDispatchers {
    Default,
    IO,
    Main,
}