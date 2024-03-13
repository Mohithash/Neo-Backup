package com.machiav3lli.backup.ui.item

import androidx.compose.ui.graphics.vector.ImageVector
import com.machiav3lli.backup.R
import com.machiav3lli.backup.ui.compose.icons.Icon
import com.machiav3lli.backup.ui.compose.icons.Phosphor

/**
 * Data class representing a single legend item in the UI.
 *
 * @property nameId The resource ID of the legend item's name string.
 * @property icon The ImageVector representation of the legend item's icon.
 * @property iconColorId The resource ID of the icon's color, optional.
 */
data class Legend(
    val nameId: Int,
    val icon: ImageVector,
    val iconColorId: Int = -1,
) {

    /** Companion object containing predefined legend items. */
    companion object {

        /** Exodus report. */
        val Exodus = Legend(
            R.string.exodus_report,
            Icon.Exodus,
            R.color.ic_exodus
        )

        /** Launch app. */
        val Launch = Legend(
            R.string.launch_app,
            Phosphor.ArrowSquareOut
        )

        /** Disable package. */
        val Disable = Legend(
            R.string.disablePackage,
            Phosphor.ProhibitInset
        )

        /** Enable package. */
        val Enable = Legend(
            R.string.enablePackage,
            Phosphor.Leaf
        )

        /** Uninstall app. */
        val Uninstall = Legend(
            R.string.uninstall,
            Phosphor.TrashSimple
        )

        /** Block package. */
        val Block = Legend(
            R.string.global_blocklist_add,
            Phosphor.Prohibit
        )

        /** System apps. */
        val System = Legend(
            R.string.radio_system,
            Phosphor.Spinner,
            R.color.ic_system
        )

        /** User apps. */
        val User = Legend(
            R.string.radio_user,
            Phosphor.User,
            R.color.ic_user
        )

        /** Special apps. */
        val Special = Legend(
            R.string.radio_special,
            Phosphor.AsteriskSimple,
            R.color.ic_special
        )

        /** APK files. */
        val APK = Legend(
            R.string.radio_apk,
            Phosphor.DiamondsFour,
            R.color.ic_apk
        )

        /** Data of apps. */
        val Data = Legend(
            R.string.radio_data,
            Phosphor.HardDrives,
            R.color.ic_data
        )

        /** Device-protected data of apps. */
        val DE_Data = Legend(
            R.string.radio_deviceprotecteddata,
            Phosphor.ShieldCheckered,
            R.color.ic_de_data
        )

        /** External data of apps. */
        val External = Legend(
            R.string.radio_externaldata,
            Phosphor.FloppyDisk,
            R.color.ic_ext_data
        )

        /** OBB files of apps. */
        val OBB = Legend(
            R.string.radio_obbdata,
            Phosphor.GameController,
            R.color.ic_obb
        )

        /** Media data of apps. */
        val Media = Legend(
            R.string.radio_mediadata,
            Phosphor.PlayCircle,
            R.color.ic_media
        )

        /** Updated apps. */
        val Updated = Legend(
            R.string.radio_updated,
            Phosphor.CircleWavyWarning,
            R.color.ic_updated
        )
    }
}
