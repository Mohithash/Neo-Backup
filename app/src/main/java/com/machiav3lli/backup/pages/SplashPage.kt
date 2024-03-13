package com.machiav3lli.backup.pages

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.BuildConfig
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.preferences.extendedInfo
import com.machiav3lli.backup.preferences.textLogShare
import com.machiav3lli.backup.ui.compose.icons.Phosphor
import com.machiav3lli.backup.ui.compose.icons.phosphor.LockOpen
import com.machiav3lli.backup.ui.compose.icons.phosphor.ShareNetwork
import com.machiav3lli.backup.ui.compose.icons.phosphor.Warning
import com.machiav3lli.backup.ui.compose.item.ElevatedActionButton
import com.machiav3lli.backup.utils.SystemUtils
import kotlin.system.exitProcess

// SplashPage: Displays the splash screen of the app
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashPage() {
    Scaffold(
        // Sets the container color to the surface container lowest color and the content color to the onSurface color
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(), // Takes up all available space in the vertical direction
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.6f)) // Adds a spacer that takes up 60% of the available space
            Image(
                modifier = Modifier
                    .fillMaxSize(0.5f), // Takes up 50% of the available space
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.app_name)
            )
            Spacer(modifier = Modifier.weight(0.4f)) // Adds a spacer that takes up 40% of the available space
            Text(
                text = listOf(
                    BuildConfig.APPLICATION_ID,
                    BuildConfig.VERSION_NAME,
                    SystemUtils.applicationIssuer?.let { "signed by $it" } ?: "",
                ).joinToString("\n"), // Displays the application ID, version name, and signing information
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// RootMissing: Displays a message when root is missing
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootMissing(activity: Activity? = null) {
    Scaffold(
        // Sets the container color to the surface container lowest color and the content color to the onSurface color
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)
        ) {
            Text(
                text = stringResource(R.string.root_missing), // Displays the root missing message
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp)) // Adds a spacer
            Text(
                text = stringResource(R.string.root_is_mandatory), // Displays the message that root is mandatory
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp)) // Adds a spacer
            Text(
                text = stringResource(R.string.see_faq), // Displays the message to see the FAQ
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(80.dp)) // Adds a spacer
            ElevatedActionButton(
                text = "try to share a support log", // Displays the text for the button
                icon = Phosphor.
