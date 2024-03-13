package research

import android.content.Context
import android.os.Environment
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.content.contentValuesOf
import androidx.documentfile.provider.DocumentFile
import androidx.test.platform.app.InstrumentationRegistry
import com.machiav3lli.backup.MIME_TYPE_FILE
import com.machiav3lli.backup.preferences.pref_shadowRootFile
import com.machiav3lli.backup.utils.TraceUtils
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader

class MyFile(private val document: DocumentFile) {

    val uri get() = document.uri
    val name get() = document.name

    val isDirectory: Boolean get() = document.isDirectory

    fun exists(): Boolean {
        return document.exists()
    }

    fun writeText(text: String) {
        try {
            val outputStream = FileOutputStream(document.uri.path)
            outputStream.write(text.toByteArray())
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readText(): String {
        val inputStream = context.contentResolver.openInputStream(document.uri)
        val reader = InputStreamReader(inputStream)
        val buffer = CharArray(1024)
        val stringBuilder = StringBuilder()
        var numCharsRead: Int

        try {
            while (reader.read(buffer).also { numCharsRead = it } != -1) {
                stringBuilder.append(buffer, 0, numCharsRead)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }

    companion object {
        fun fromFile(file: File, context: Context): MyFile {
            return MyFile(DocumentFile.fromSingleUri(context, file.uri))
        }
    }

    fun createFile(name: String): MyFile? {
        val file = document.createFile(MIME_TYPE_FILE, name)
        file?.let {
            if (it.name != name)
                it.renameTo(name)   // need to delete existing target
            return MyFile(file)
        }
        return null
    }

    fun createDirectory(name: String): MyFile? {
        val dir = document.createDirectory(name)
        dir?.let {
            if (it.name != name)
                it.renameTo(name)   // need to delete existing target
            return MyFile(dir)
        }
        return null
    }

    fun delete(): Boolean {
        if (isDirectory && document.listFiles().isNotEmpty())
            return false
        return document.delete()
    }

    fun deleteRecursive(): Boolean {
        return document.delete()
    }

    fun listFiles(): List<MyFile> {
        return document.listFiles().map { MyFile(it) }
    }
}

class Try_DocumentFile {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    val baseDirAsFile = context.externalCacheDir!!
    val baseDir = MyFile.fromFile(baseDirAsFile, context)

    val testDirName get() = TraceUtils.classAndMethodName().replace(':', '_')
    val testFileName = "test.txt"
    val testText1 = "test text 1"
    val testText2 = "test text 2"
    val testText = "test text"

    @Before
    fun setup() {
        pref_shadowRootFile.value = false
    }

    @Test
    fun test_DeleteFile() {

        val dir = baseDir.createDirectory(testDirName)!!

        assertEquals(testDirName, dir.name)

        val file = dir.createFile(testFileName)!!

        assertEquals(testFileName, file.name)

        file.writeText(testText)

        assertEquals(true, dir.exists())
        assertEquals(true, dir.isDirectory)
        assertEquals(testText, file.readText())
        assertEquals(1, dir.listFiles().count())
        assertEquals(testFileName, dir.listFiles().first().name)

        file.delete()

        assertEquals(false, dir.exists())
        assertEquals(false, dir.isDirectory)
    }

    @Test
    fun test_DuplicateDir() {

        val dir = baseDir.createDirectory(testDirName)!!

        assertEquals(testDirName, dir.name)

        val file = dir.createFile(testFileName)!!

        assertEquals(testFileName, file.name)

        file.writeText(testText)

        assertEquals(true, dir.exists())
        assertEquals(true, dir.isDirectory)
        assertEquals(testText, file.readText())
        assertEquals(1, dir.listFiles().count())
        assertEquals(testFileName, dir.listFiles().first().name)

        val dir2 = baseDir.createDirectory(testDirName)!!   // create existing dir

        assertEquals(true, dir2.exists())
        assertEquals(true, dir2.isDirectory)
        assertEquals(testText, file.readText())
        assertEquals(1, dir.listFiles().count())
        assertEquals(testFileName, dir.listFiles().first().name)

        dir.deleteRecursive()

        assertEquals(false, dir.exists())
        assertEquals(false, dir.isDirectory)
        assertEquals(false, dir2.exists())
        assertEquals(false, dir2.isDirectory)
    }


    @Test
    fun test_OverwriteFile() {

        val dir = baseDir.createDirectory(testDirName)!!

        assertEquals(testDirName, dir.name)

        val file1 = dir.createFile(testFileName)!!

        assertEquals(testFileName, file1.name)

        file1.writeText(testText1)

        assertEquals(true, dir.
