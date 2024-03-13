package research

import com.machiav3lli.backup.handler.ShellHandler.Companion.quote
import com.machiav3lli.backup.handler.ShellHandler.Companion.utilBoxQ
import com.topjohnwu.superuser.ShellUtils.fastCmd
import com.topjohnwu.superuser.io.SuFile
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class Try_SuFile {

    private lateinit var fileJ: File

    @Before
    fun beforeTest() {
        fileJ = File.createTempFile("Try_File", ".test")
        val fileX = SuFile("/cache/Try_SuFile.test")

        // init
        listOf(
            { it: File ->
                try {
                    fastCmd("touch ${it.absolutePath}")
                    fastCmd("chmod 000 ${it.absolutePath}")
                } catch (e: Exception) {
                    println("Failed to initialize file ${it.absolutePath}: ${e.message}")
                }
            },
            // 000
            {
                try {
                    fastCmd("chmod 000 ${it.absolutePath}")
                    it.setWritable(false, false)
                } catch (e: Exception) {
                    println("Failed to set file ${it.absolutePath} to 000: ${e.message}")
                }
            },
            {
                try {
                    fastCmd("chmod 000 ${it.absolutePath}")
                    it.setWritable(false, true)
                } catch (e: Exception) {
                    println("Failed to set file ${it.absolutePath} to 000: ${e.message}")
                }
            },
            {
                try {
                    fastCmd("chmod 000 ${it.absolutePath}")
                    it.setWritable(true, false)
                } catch (e: Exception) {
                    println("Failed to set file ${it.absolutePath} to 000: ${e.message}")
               
