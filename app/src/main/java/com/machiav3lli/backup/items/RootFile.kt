package com.machiav3lli.backup.items

import com.machiav3lli.backup.handler.ShellHandler
import com.topjohnwu.superuser.Shell
import com.topjohnwu.superuser.ShellUtils
import com.topjohnwu.superuser.io.SuFile
import com.topjohnwu.superuser.io.SuFileInputStream
import com.topjohnwu.superuser.io.SuFileOutputStream
import java.io.File
import java.io.FileFilter
import java.io.FilenameFilter
import java.io.InputStream
import java.io.OutputStream
import java.net.URI
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * A [SuFile] implementation using OAndBackupX standards (utilBox, quoting, etc.)
 */
class RootFile internal constructor(file: File?) : File(file!!.absolutePath) {

    constructor(pathname: String) : this(File(pathname))

    constructor(parent: String?, child: String) : this(File(parent, child))

    constructor(parent: File?, child: String) : this(parent?.absolutePath, child)

    constructor(uri: URI) : this(File(uri))

    // ... rest of the class

    companion object {

        fun cmd(c: String): String = ShellUtils.fastCmd(c)

        fun cmdBool(c: String): Boolean = ShellUtils.fastCmdResult(c)

        fun open(pathname: String): File {
            return if (Shell.isAppGrantedRoot() == true) RootFile(pathname) else File(pathname)
        }

        fun open(parent: String?, child: String): File {
            return if (Shell.isAppGrantedRoot() == true) RootFile(parent, child) else File(parent, child)
        }

        fun open(parent: File?, child: String): File {
            return if (Shell.isAppGrantedRoot() == true) RootFile(parent, child) else File(parent, child)
        }

        fun open(uri: URI): File {
            return if (Shell.isAppGrantedRoot() == true) RootFile(uri) else File(uri)
        }
    }
}


// ... rest of the class methods

override fun canExecute(): Boolean {
        return runCatching { cmdBool("[ -x $quoted ]") }.getOrDefault(false)
    }

    override fun canRead(): Boolean {
        return runCatching { cmdBool("[ -r $quoted ]") }.getOrDefault(false)
    }

    override fun canWrite(): Boolean {
        return runCatching { cmdBool("[ -w $quoted ]") }.getOrDefault(false)
    }

    override fun createNewFile(): Boolean {
        return runCatching {
            cmdBool("[ ! -e $quoted ] && $utilBoxQ echo -n > $quoted")
        }.getOrDefault(false)
    }

    @Throws(IOException::class)
    override fun delete(): Boolean {
        return runCatching {
            cmdBool("$utilBoxQ rm -f $quoted || $utilBoxQ rmdir $quoted")
        }.getOrDefault(false)
    }

    @Throws(IOException::class)
    fun deleteRecursive(): Boolean {
        return runCatching { cmdBool("$utilBoxQ rm -rf $quoted") }.getOrDefault(false)
    }

    @Throws(IOException::class)
    fun clear(): Boolean = runCatching { cmdBool("$utilBoxQ echo -n > $quoted") }.getOrDefault(false)

    // ... rest of the class methods

    override fun getCanonicalPath(): String {
        return runCatching {
            val path = cmd("$utilBoxQ readlink -e $quoted")
            if (path.isEmpty()) getPath() else path
        }.getOrDefault("")
    }

    // ... rest of the class methods

    override fun getParentFile(): RootFile? {
        return parent?.let { RootFile(it) }
    }

    // ... rest of the class methods

    override fun getFreeSpace(): Long = runCatching { statFS("%f") }.getOrDefault(Long.MAX_VALUE)

    override fun getTotalSpace(): Long = runCatching { statFS("%b") }.getOrDefault(Long.MAX_VALUE)

    override fun getUsableSpace(): Long = runCatching { statFS("%a") }.getOrDefault(Long.MAX_VALUE)

    // ... rest of the class methods

    override fun exists(): Boolean {
        return runCatching { cmdBool("[ -e $quoted ]") }.getOrDefault(false)
    }

    override fun isDirectory(): Boolean {
        return runCatching { cmdBool("[ -d $quoted ]") }.getOrDefault(false)
    }

    override fun isFile(): Boolean {
        return runCatching { cmdBool("[ -f $quoted ]") }.getOrDefault(false)
    }

    // ... rest of the class methods

    override fun lastModified(): Long {
        return runCatching {
            cmd("$utilBoxQ stat -c '%Y' $quoted").toLong() * 1000
        }.getOrDefault(0L)
    }

    override fun length(): Long {
        return runCatching { cmd("$utilBoxQ stat -c '%s' $quoted").toLong()
