/*
 * Neo Backup: open-source apps backup and restore app.
 * Copyright (C) 2020  Antonios Hazim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.machiav3lli.backup.pages

import android.net.Uri
import androidx.activity.compose.LocalUriHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.R
import com.machiav3lli.backup.activities.MainActivityX
import com.machiav3lli.backup.linksList
import com.machiav3lli.backup.ui.compose.blockBorder
import com.machiav3lli.backup.ui.compose.icons.Phosphor
import com.machiav3lli.backup.ui.compose.icons.phosphor.ArrowRight
import com.machiav3lli.backup.ui.compose.item.ElevatedActionButton
import com.machiav3lli.backup.ui.compose.item.LinkItem
import com.machiav3lli.backup.ui.compose.item.TopBar
import com.machiav3lli.backup.ui.navigation.NavItem

// WelcomePage: Composable function for the welcome page of the Neo Backup app
@Composable
fun WelcomePage() {
    // Get the current context
    val context = LocalContext.current

    // Surface: Create a surface with transparent background and onSurface text color
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        // Column: Create a column for the main content
        Column(
            modifier = Modifier
                .padding(16.dp)
                .blockBorder(),
        ) {
            // TopBar: Create a top bar with the app name as the title
            TopBar(title = stringResource(id = R.string.app_name)) {}

            // LazyColumn: Create a lazy column for the list of links
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                // Item: Create a card for the list of links
                items(linksList) { link ->
                    LinkItem(
                        modifier = Modifier.fillMaxWidth(),
                        item = link,
                        weight = 1f,
                        onClick = { uriString ->
                            // onClick: Use LocalUriHandler to handle URIs in a more idiomatic way
                            val uriHandler = LocalUriHandler.current
                            uriHandler.openUri(uriString)
                        }
                    )
                }
            }

            // Spacer: Add some space before the bottom bar
            Spacer(modifier = Modifier.weight(1f))

            // LazyRow: Create a lazy row for the bottom bar with an ElevatedActionButton
            LazyRow(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.Center
            ) {
                item {
                    ElevatedActionButton(
                        // ElevatedActionButton: Create an action button with the text "dialog_start" and ArrowRight icon
                        modifier = Modifier.padding(horizontal = 24.dp),
                        text = stringResource(id = R.string.dialog_start),
                        icon =
