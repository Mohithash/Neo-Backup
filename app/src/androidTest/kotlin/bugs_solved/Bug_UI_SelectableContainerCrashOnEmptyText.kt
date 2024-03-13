package tests.bugs_solved

import android.util.Log
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.TestTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class Bug_UI_SelectableContainerCrashOnEmptyText {

    private val durationSwipe = 500L
    private val durationLongPress = 500L

    @Composable
    fun SelectableText(@RawRes resourceId: Int) {
        val text = getRawResourceString(resourceId)
        Surface {
            Column {
                SelectionContainer {
                    Column {
                        Text("simple", modifier = TestTag("simple"))
                        Text(text = text, modifier = TestTag("content"))
                    }
                }
                SelectionContainer {
                    Column {
                        Text("space", modifier = TestTag("space"))
                        text.lines().forEach {
                            Text(
                                text = if (it == "") " " else it,
                                modifier = TestTag("content")
                            )
                        }
                    }
                }
                SelectionContainer {
                    Column {
                        Text("crash", modifier = TestTag("crash"))
                        text.lines().forEach {
                            Text(text = it, modifier = TestTag("content"))
                        }
                    }
                }
            }
        }
    }

    @Rule
    @JvmField
    var test: ComposeContentTestRule = createComposeRule()

    @Before
    fun setUp() {
        test.setContent {
            SelectableText(R.raw.test_text)
        }
        test.onRoot().printToLog("root")
    }

    private fun getRawResourceString(@RawRes resourceId: Int): String {
        return test.context.resources.openRawResource(resourceId).bufferedReader().use {
            it.readText()
        }
    }

    private fun SemanticsNodeInteraction.swipeVertical(
        startOffset: Offset,
        endOffset: Offset,
        duration: Long
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            down(startOffset)
            delay(durationLongPress)
            for (i in 0 until (durationSwipe / duration).toInt()) {
                val fraction = i / (durationSwipe / duration).toFloat()
                val currentOffset = Offset(
                    startOffset.x + (endOffset.x - startOffset.x) * fraction,
                    startOffset.y + (endOffset.y - startOffset.y) * fraction
                )
                move(currentOffset)
                delay(duration)
            }
            up()
        }
    }

    @Test
    fun works_simple() {
        val anchor = test.onNodeWithTag("simple")
        val column = anchor.onParent()
        column.printToLog("simple")
        anchor.performTouchInput { swipeVertical(center, bottomCenter, durationSwipe) }
    }

    @Test
    fun crash() {
        val anchor = test.onNodeWithTag("crash")
        val column = anchor.onParent()
        column.printToLog("crash")
        anchor.performTouchInput { swipeVertical(center, bottomCenter, durationSwipe) }
    }

    @Test
    fun works_space() {
        val anchor = test.onNodeWithTag("space")
        val column = anchor.onParent()
        column.printToLog("space")
        anchor.performTouchInput { swipeVertical(center, bottomCenter, durationSwipe) }
    }
}
