import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.R
import com.machiav3lli.backup.ui.compose.item.ActionButton
import com.machiav3lli.backup.ui.compose.item.ElevatedActionButton
import kotlin.math.roundToInt

typealias IntPickerDialogProperties = (
    value: Int,
    defaultValue: Int,
    entries: List<Int>,
    openDialogCustom: MutableState<Boolean>,
    onSave: (Int) -> Unit,
)

@Composable
fun IntPickerDialogUI(
    properties: IntPickerDialogProperties,
) {
    val context = LocalContext.current
    var currentValue by remember {
        mutableIntStateOf(properties.value)
    }
    var sliderIndex by remember {
        mutableIntStateOf(
            getSliderIndex(
                currentValue = currentValue,
                defaultValue = properties.defaultValue,
                entries = properties.entries
            )
        )
    }

    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.sched_interval),
                style = MaterialTheme.typography.titleLarge
            )
            Row {
                Slider(
                    modifier = Modifier.weight(1f, false),
                    value = sliderIndex.toFloat(),
                    valueRange = 0f..(properties.entries.size - 1).toFloat(),
                    onValueChange = {
                        sliderIndex = it.roundToInt()
                        currentValue = properties.entries[sliderIndex]
                    },
                    steps = properties.entries.size - 1,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                    )
                )
                Spacer(modifier = Modifier.requiredWidth(8.dp))
                Text(
                    text = currentValue.toString(),
                    modifier = Modifier.widthIn(min = 48.dp)
                )
            }
            Row(
                Modifier.fillMaxWidth()
            ) {
                ActionButton(text = stringResource(id = R.string.dialogCancel)) {
                    properties.openDialogCustom.value = false
                }
                Spacer(Modifier.weight(1f))
                ElevatedActionButton(text = stringResource(id = R.string.dialogSave)) {
                    properties.onSave(properties.entries[sliderIndex])
                    properties.openDialogCustom.value = false
                }
            }
        }
    }
}

private fun getSliderIndex(
    currentValue: Int,
    defaultValue: Int,
    entries: List<Int>,
): Int {
    return entries.indexOfFirst { it >= currentValue }
        .takeUnless { it < 0 }
        ?: entries.indexOf(defaultValue)
        .coerceIn(0, entries.size - 1)
}
