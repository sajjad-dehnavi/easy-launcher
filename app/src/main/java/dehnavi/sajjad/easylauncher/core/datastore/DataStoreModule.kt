package dehnavi.sajjad.easylauncher.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dehnavi.sajjad.easylauncher.core.model.AppLocalData
import dehnavi.sajjad.easylauncher.core.utils.dispacher.AppDispatchers
import dehnavi.sajjad.easylauncher.core.utils.dispacher.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providesAppLocalDataStore(
        @ApplicationContext context: Context,
        serializer: AppLocalDataSerializer,
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher
    ): DataStore<AppLocalData> =
        DataStoreFactory.create(
            serializer = serializer,
            scope = CoroutineScope(ioDispatcher),
        ) {
            context.dataStoreFile("app_data.pb")
        }
}