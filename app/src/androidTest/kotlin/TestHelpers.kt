package tests

import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import timber.log.Timber

fun ComposeContentTestRule.waitUntilNodeCount(
    matcher: SemanticsMatcher,
    count: Int,
    timeoutMillis: Long = 1_000L
) {
    this.waitUntil(timeoutMillis) {
        this.onAllNodes(matcher).fetchSemanticsNodes().size == count
    }
}

@OptIn(ExperimentalTestApi::class)
fun ComposeContentTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 1_000L
) {
    waitUntilNodeCount(matcher, 1, timeoutMillis)
}

@OptIn(ExperimentalTestApi::class)
fun ComposeContentTestRule.waitUntilDoesNotExist(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 1_000L
) {
    waitUntilNodeCount(matcher, 0, timeoutMillis)
}

fun ComposeContentTestRule.onNodeWait(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 1_000L
): SemanticsNodeInteraction? {
    var node: SemanticsNodeInteraction? = null
    try {
        this.waitUntil(timeoutMillis) {
            val nodes = this.onAllNodes(matcher)
            if (nodes.fetchSemanticsNodes().isNotEmpty()) {
                node = nodes.onFirst()
                true
            } else {
                false
            }
        }
    } catch (e: ComposeTimeoutException) {
        Timber.d("----------", "Timeout onNodeWait($matcher, $timeoutMillis)")
        return null
    }
    return node
}

@OptIn(ExperimentalTestApi::class)
fun ComposeContentTestRule.onNodeWaitOrAssert(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 1_000L,
    assert: Boolean = false
): SemanticsNodeInteraction {
    val node = onNodeWait(matcher, timeoutMillis)
    if (node == null && assert) {
        throw AssertionError("node with ${matcher.description} does not exist")
    }
    return node ?: throw AssertionError("node with ${matcher.description} does not exist")
}
