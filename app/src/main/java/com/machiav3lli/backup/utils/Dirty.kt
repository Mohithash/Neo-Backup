package com.machiav3lli.backup.utils

import androidx.core.text.isDigitsOnly

// This class, Dirty, contains a collection of "dirty" values and functions. These are essentially
// hard-coded, potentially version-dependent values and assumptions that could be improved upon.
// They are referred to as "dirty" because they are not necessarily future-proof or portable.
class Dirty {
    // The companion object for the Dirty class contains a few constants and functions.
    companion object {
        // These constants represent the first and last application IDs. The assumption here is that
        // the group ID (GID) is equal to the user ID (UID), which is usually the case.
        val FIRST_APPLICATION_GID = android.os.Process.FIRST_APPLICATION_UID  //TODO ASSUMPTION: gid==uid (usually 10000)
        val LAST_APPLICATION_GID  = android.os.Process.LAST_APPLICATION_UID   //TODO ASSUMPTION: gid==uid (usually 19999)

        // This constant represents the first cache group ID, which is assumed to follow the normal
        // group of application IDs.
        val FIRST_APPLICATION_CACHE_GID = LAST_APPLICATION_GID+1              //TODO ASSUMPTION: cache group following normal group

        // This function converts an application group ID to a cache group ID.
        fun appGidToCacheGid(appGid: Int) = appGid - FIRST_APPLICATION_GID + FIRST_APPLICATION_CACHE_GID

        // This function converts an application group ID (either a string or an integer) to a cache
        // group ID. If the input is a string, it first checks if it's all digits. If it is, it converts
        // it to an integer and proceeds as before. If it's not all digits, it tries to get the numeric
        // GID for the name. If the name doesn't correspond to a valid GID, it returns the original name.
        fun appGidToCacheGid(appGid: String): String {
            return if (appGid.isDigitsOnly()) {
                val numGid = appGid.toInt()
                if (numGid < FIRST_APPLICATION_GID || LAST_APPLICATION_GID < numGid) // something special like sdcard_rw
                    appGid
                else
                    "${appGidToCacheGid(numGid)}"
            } else {
                val numGid = android.os.Process.getGidForName(appGid)
                if (numGid < FIRST_APPLICATION_GID || LAST_APPLICATION_GID < numGid) // something special like sdcard_rw
                //if (!appGid.matches(Regex("""u\d+_a\d+""")))                       // one more assumption
                    appGid
                else
                    "${appGid}_cache"
            }
        }
    }
}

