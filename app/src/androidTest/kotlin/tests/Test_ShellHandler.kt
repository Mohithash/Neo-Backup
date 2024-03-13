package tests

import com.machiav3lli.backup.handler.ShellHandler
import com.machiav3lli.backup.handler.ShellHandler.Companion.runAsRoot
import com.machiav3lli.backup.handler.ShellHandler.Companion.runAsRootPipeInCollectErr
import com.machiav3lli.backup.handler.ShellHandler.Companion.runAsRootPipeOutCollectErr
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class Test_ShellHandler {

    // This test checks if the fromLsOutput method of ShellHandler.FileInfo can handle whitespace in the file path
    @Test
    fun test_fromLsOutput_canHandleWhitespace() {
        // The input string for the fromLsOutput method
        val input = "-rw------- 1 user0_a247 group0_a247 15951095 2021-01-19 01:03:29.000000000 +0100 Aurora Store-3.2.8.apk"
        // The base directory for the file path
        val baseDirectory = "/data/data/org.fdroid.fdroid/files"

        // Call the fromLsOutput method with the input string and base directory
        val fileInfo = ShellHandler.FileInfo.fromLsOutput(input, baseDirectory)

        // Check if the fileInfo object is not null
        assertNotNull(fileInfo!!)

        // Check if the filePath, absolutePath, and fileSize properties of the fileInfo object are correct
        assertEquals(
            "Aurora Store-3.2.8.apk",
            fileInfo.filePath
        )
        assertEquals(
            "/data/data/org.fdroid.fdroid/files/Aurora Store-3.2.8.apk",
            fileInfo.absolutePath
        )
        assertEquals(
            15951095,
            fileInfo.fileSize
        )

        // Check if the fileType property of the fileInfo object is correct
        assertEquals(
            ShellHandler.FileType.REGULAR_FILE,
            fileInfo.fileType
        )
    }

    // This test checks if the fromLsOutput method of ShellHandler.FileInfo can handle multiple whitespaces in the file path
    @Test
    fun test_fromLsOutput_canHandleMultiWhitespace() {
        // The input string for the fromLsOutput method
        val input = "-rw------- 1 user0_a247 group0_a247 15951095 2021-01-19 01:03:29.000000000 +0100 111   333.file"
        // The base directory for the file path
        val baseDirectory = "/data/data/org.fdroid.fdroid/files"

        // Call the fromLsOutput method with the input string and base directory
        val fileInfo = ShellHandler.FileInfo.fromLsOutput(input, baseDirectory)

        // Check if the fileInfo object is not null
        assertNotNull(fileInfo!!)

        // Check if the filePath, absolutePath, fileSize, owner, group, fileModTime, fileMode, and fileType properties of the fileInfo object are correct
        assertEquals(
            "111   333.file",
            fileInfo.filePath
        )
        assertEquals(
            "/data/data/org.fdroid.fdroid/files/111   333.file",
            fileInfo.absolutePath
        )
        assertEquals(
            15951095,
            fileInfo.fileSize
        )
        assertEquals(
            "user0_a247",
            fileInfo.owner
        )
        assertEquals(
            "group0_a247",
            fileInfo.group
        )
        assertEquals(
            1611014609000,
            fileInfo.fileModTime.time
        )
        assertEquals(
            0b0_110_000_000,
            fileInfo.fileMode
        )
        assertEquals(
            ShellHandler.FileType.REGULAR_FILE,
            fileInfo.fileType
        )
    }

    // This test checks if the fromLsOutput method of ShellHandler.FileInfo can handle the old format of the ls output
    @Test
    fun test_fromLsOutput_canHandleOldFormat() {
        // The input string for the fromLsOutput method
        val input = "-rw------- 1 user0_a247 group0_a247 15951095 2021-01-19 01:03 111   333.file"
        // The base directory for the file path
        val baseDirectory = "/data/data/org.fdroid.fdroid/files"

        // Call the fromLsOutput method with the input string and base directory
        val fileInfo = ShellHandler.FileInfo.fromLsOutput(input, baseDirectory)

        // Check if the fileInfo object is not null
        assertNotNull(fileInfo!!)

        // Check if the filePath, absolutePath, fileSize, owner, group, fileModTime, fileMode, and fileType properties of the fileInfo object are correct
        assertEquals(
            "111   333.file",
            fileInfo.filePath
        )
        assertEquals(
            "/data/data/org.fdroid.fdroid/files/111   333.file",
            fileInfo.absolutePath
        )
        assertEquals(
            15951095,
            fileInfo.fileSize
        )
        assertEquals(
            "user0_a247",
            fileInfo.owner
        )
        assertEquals(
            "group0_a247",
            fileInfo.group
        )
