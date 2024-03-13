package tests

import com.machiav3lli.backup.handler.ShellHandler
import com.machiav3lli.backup.handler.ShellHandler.Companion.runAsRoot
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.time.Instant
import java.time.ZoneOffset

class Test_ShellHandler {

    // This test checks if the fromLsOutput method of ShellHandler.FileInfo can handle whitespace in the file path
    @Test
    fun test_fromLsOutput_canHandleWhitespace() = runBlocking {
        // The input string for the fromLsOutput method
        val input = "-rw------- 1 user0_a247 group0_a247 15951
