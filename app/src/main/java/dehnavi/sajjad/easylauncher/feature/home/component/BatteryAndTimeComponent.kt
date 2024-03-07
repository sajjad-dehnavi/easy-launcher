package dehnavi.sajjad.easylauncher.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dehnavi.sajjad.easylauncher.core.model.AppLocalData

@Composable
fun BatteryAndTimeComponent(
    modifier: Modifier = Modifier,
    appLocalData: AppLocalData,
) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    Column(
        modifier = modifier
            .drawWithCache {
                onDrawBehind {
                    drawArc(
                        color = onBackgroundColor,
                        startAngle = -90f,
                        sweepAngle = appLocalData.batteryCharge * 3.6f,
                        useCenter = false,
                        style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
            }
            .fillMaxWidth(0.45f)
            .aspectRatio(1f / 1f)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = appLocalData.concatTime(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = appLocalData.concatDate(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
        )
    }
}