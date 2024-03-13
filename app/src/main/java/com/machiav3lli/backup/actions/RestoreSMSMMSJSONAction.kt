import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.provider.Telephony
import androidx.annotation.RequiresApi
import androidx.annotation.Suppress
import androidx.core.content.PermissionChecker
import com.machiav3lli.backup.utils.safePut
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.*

object RestoreSMSMMSJSONAction {

    private var currentThreadId: Long = 0
    private val compareForNewThread: Long = 0

    @SuppressLint("MissingPermission")
    fun restoreData(context: Context, filePath: String) {
        if (!context.packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
            throw RuntimeException("Device does not have SMS/MMS.")
        }
        if (!isGranted(context, Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS, Manifest.permission.RECEIVE_MMS,
                Manifest.permission.RECEIVE_WAP_PUSH)) {
            throw RuntimeException("No permission for SMS/MMS.")
        }
        if (!isDefaultSms(context)) {
            throw RuntimeException("OAndBackupX not default SMS/MMS app.")
        }

        context.contentResolver.openInputStream(Uri.fromFile(File(filePath)))?.use { inputStream ->
            inputStream.reader(StandardCharsets.UTF_8)?.use { reader ->
                restoreThreads(context, reader)
            }
        }
    }

    // Loop through Threads
    private fun restoreThreads(context: Context, reader: BufferedReader) {
        reader.readLine() // Skip the first line
        reader.use {
            var line: String?
            while ({ line = it.readLine(); line }() != null) {
                parseThread(context, line)
            }
        }
    }

    // Parse through one Thread
    private fun parseThread(context: Context, line: String) {
        val values = ContentValues().apply {
            line.split(",").forEach {
                val parts = it.split("=")
                safePut(parts[0], parts[1].replace("\"", ""))
            }
        }
        currentThreadId = if (currentThreadId == compareForNewThread) {
            Telephony.Threads.getOrCreateThreadId(context, values.getAsString(Telephony.Sms.ADDRESS))
        } else {
            currentThreadId
        }
        values.put(Telephony.Sms.THREAD_ID, currentThreadId)
        saveSMS(context, values, "${Telephony.Sms.THREAD_ID} = $currentThreadId")
    }

    // Save single SMS to database
    private fun saveSMS(context: Context, values: ContentValues, queryWhere: String) {
        context.contentResolver.query(Telephony.Sms.CONTENT_URI, null, queryWhere, null, null)?.use { cursor ->
            if (cursor.count == 0) {
                context.contentResolver.insert(Telephony.Sms.CONTENT_URI, values)
            }
        }
    }

    // Check if default SMS
    private fun isDefaultSms(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.getSystemService(RoleManager::class.java)?.isRoleHeld(RoleManager.ROLE_SMS) == true
        } else {
            Telephony.Sms.getDefaultSmsPackage(context) == context.packageName
        }
    }

    // Check if permission is granted
    private fun isGranted(context: Context, vararg permissions: String): Boolean {
        permissions.forEach { permission ->
            if (PermissionChecker.checkCallingOrSelfPermission(context, permission) == PermissionChecker.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }
}

@Suppress("UNUSED")
private fun InputStream.reader(charset: Charsets): BufferedReader {
    return BufferedReader(InputStreamReader(this, charset))
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
@Suppress("SameParameterValue")
private fun ContentResolver.saveMMSPartImage(context: Context, values: ContentValues, inputStream: InputStream) {
    val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Telephony.Mms.Part.getPartUriForMessage(values.getAsString(Telephony.Mms.Part.MSG_ID))
    } else {
        Uri.parse("content://mms/${values.getAsString(Telephony.Mms.Part.MSG_ID)}/part")
    }

    context.contentResolver.insert(uri, values)?.also { uri ->
        ParcelFileDescriptor.open(uri, ParcelFileDescriptor.MODE_WRITE_
