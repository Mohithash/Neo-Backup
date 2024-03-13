package com.machiav3lli.backup.ui.compose.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

/**
 * Constant defining the interval by which the text size is reduced when it doesn't fit the available
 * space.
 */
private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun AutoScalingText(
    text: String,
    style: TextStyle,
    maxLines: Int = Int.MAX_VALUE,
    onTextSizeChange: (TextUnit) -> Unit = {},
) {
    var textSize by remember { mutableStateOf(style.fontSize) }

    LaunchedEffect(text, style, maxLines) {
        var availableWidth = 0
        var textWidth = 0

        with(MaterialTheme) {
            availableWidth = (fillMaxWidth().width - paddingStart - paddingEnd).value.toInt()
            textWidth = textMeasurer.measure(text, style.copy(fontSize = textSize)).size.width
        }

        while (textWidth > availableWidth && textSize > TextUnit.Unspecified) {
            textSize *= TEXT_SCALE_REDUCTION_INTERVAL
            textWidth = textMeasurer.measure(text, style.copy(fontSize = textSize)).size.width
        }
