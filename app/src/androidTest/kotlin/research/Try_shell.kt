package research

import com.machiav3lli.backup.handler.ShellHandler
import com.machiav3lli.backup.handler.ShellHandler.Companion.runAsRoot
import org.jetbrains.annotations.TestOnly
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.DisplayName
import org.junit.RunWith
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import timber.log.Timber
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.max
import kotlin.math.pow

@RunWith(MockitoJUnitRunner::class)
class Try_shell {

    @Mock
    lateinit var shellHandler: ShellHandler

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(shellHandler.runAsRoot(anyString())).thenCallRealMethod()
    }

    @After
    fun tearDown() {
        Mockito.reset(shellHandler)
    }

    @TestOnly
    fun findShellCommandLength(commandStub: String?, check: String? = "api", maxLengthChecked: Int = 999_999): Int? {
        // ...
    }

    @Test
    @DisplayName("test_shellCommandLength_API")
    fun test_shellCommandLength_API() {
        // ...
    }

    @Test
    @DisplayName("test_shellCommandLength_Shell")
    fun test_shellCommandLength_Shell() {
        // ...
    }

    @Test
    @DisplayName("test_escapes")
    @Suppress("DEPRECATION")
    fun test_escapes() {
        // ...
    }

    @Test
    @DisplayName("test_shell_stdout_stderr_are_separate")
    fun test_shell_stdout_stderr_are_separate() {
        // ...
    }
}
