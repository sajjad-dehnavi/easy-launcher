package dehnavi.sajjad.easylauncher.core.datastore

import androidx.datastore.core.Serializer
import dehnavi.sajjad.easylauncher.core.model.AppLocalData
import dehnavi.sajjad.easylauncher.core.utils.dispacher.AppDispatchers
import dehnavi.sajjad.easylauncher.core.utils.dispacher.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class AppLocalDataSerializer @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : Serializer<AppLocalData> {
    override val defaultValue: AppLocalData
        get() = AppLocalData()

    override suspend fun readFrom(input: InputStream): AppLocalData = withContext(ioDispatcher) {
        try {
            Json.decodeFromString<AppLocalData>(input.readBytes().decodeToString())
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppLocalData, output: OutputStream) {
        withContext(ioDispatcher) {
            output.write(Json.encodeToString(t).encodeToByteArray())
        }
    }

}