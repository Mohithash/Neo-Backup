package research

import com.machiav3lli.backup.handler.ShellHandler.Companion.quote
import com.machiav3lli.backup.handler.ShellHandler.Companion.utilBoxQ
import com.topjohnwu.superuser.ShellUtils.fastCmd
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class Try_File {

    // Create a temporary file with a unique name and .test extension
    val file = File.createTempFile("Try_File", ".test")

    @Test
    fun test_File_ownerOnly() {

        // Create the file
        fastCmd("touch ${file.absolutePath}")

        // Set the file to be only writable by the owner
        fastCmd("chmod 0 ${file.absolutePath}")
        assertEquals(
            "0",  // The file permissions should be 0 (only owner can read, write, and execute)
            fastCmd("$utilBoxQ stat -c '%a' ${quote(file)}")
        )

        // Test various scenarios where the file is and isn't writable
        fastCmd("chmod 0 ${file.absolutePath}")
        file.setWritable(false, true)  // Set the file to be not writable by anyone
        assertEquals(
            "0",  // The file permissions should still be 0
            fastCmd("$utilBoxQ stat -c '%a' ${quote(file)}")
        )

        fastCmd("chmod 0 ${file.absolutePath}")
        file.setWritable(false, false)  // Set the file to be not writable by anyone, even the owner
        assertEquals(
            "0",  // The file permissions should still be 0
            fastCmd("$utilBoxQ stat -c '%a' ${quote(file)}")
        )

        fastCmd("chmod 0 ${file.absolutePath}")
        file.setWritable(true, true)  // Set the file to be writable by the owner
        assertEquals(
            "200",  // The file permissions should now be 200 (owner can read and write, others can only execute)
            fastCmd("$utilBoxQ stat -c '%a' ${quote(file)}")
        )

        fastCmd("chmod 0 ${file.absolutePath}")
        file.setWritable(true, false)  // Set the file to be writable by the owner, but not by others
        assertEquals(
            "222",  // The file permissions should now be 222 (owner can read, write, and execute, others can't)
            fastCmd("$utilBoxQ stat -c '%a' ${quote(file)}")
        )

        // Reset the file to be writable by everyone

        fastCmd("chmod 777 ${file.absolutePath}")
        assertEquals(
            "777",  // The file permissions should now be 777 (read, write, and execute for everyone)
            fastCmd("$utilBoxQ stat -c '%a' ${quote(file)}")
        )

        fastCmd("chmod 777 ${file.absolutePath}")
        file.setWritable(false, true)  // Set the file to be not writable by anyone
        assertEquals(
            "577",  // The file permissions should now be 577 (owner can read, write, and execute, others can read and execute)
            fastCmd("$utilBoxQ stat -c '%a' ${quote(file)}")
        )

        fastCmd("chmod 777 ${file.absolutePath}")
        file.setWritable(false, false)  // Set the file to be not writable by anyone, even the owner
        assertEquals(
            "555",  // The file permissions should now be 555 (owner can read, write, and execute, others can only read and execute)
            fastCmd("$utilBoxQ stat -c '%a' ${quote(file)}")
        )

        file.delete()  // Delete the file
    }

    @Test

