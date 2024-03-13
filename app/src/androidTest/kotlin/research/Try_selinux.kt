package research

import com.machiav3lli.backup.handler.ShellHandler
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import timber.log.Timber

class TrySelinux {

    private val commandLSBdAZ = "toybox ls -bdAZ /"
    private val commandLSBdAlZ = "toybox ls -bdAlZ /"
    private val commandStatC = "toybox stat -c '%%U %%G %%C' /"

    @Test
    fun testLsBdAZ() {
        val (output, _) = ShellHandler.runAsRoot(commandLSBdAZ)
        val context = output.split(" ", limit = 2)[0]
        Timber.i("$commandLSBdAZ -> $output -> context = $context")

        assertNotNull(context)
        assertNotEquals("?", context)
    }

    @Test
    fun testLsBdAlZ() {
        val (output, _) = ShellHandler.runAsRoot(commandLSBdAlZ)
        val context = output.split(" ", limit = 6)[4]
        Timber.i("$commandLSBdAlZ -> $output -> context = $context")

        assertNotNull(context)
        assertNotEquals("?", context)
    }

    @Test
    fun testStatC() {
        val (output, _) = ShellHandler.runAsRoot(commandStatC)
        val context = output.split(" ", limit = 3)[2]
        Timber.i("$commandStatC -> $output -> context = $context")

        assertNotNull(context)
        assertNotEquals("?", context)
    }
}
