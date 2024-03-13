/*
 * Neo Backup: open-source apps backup and restore app.
 * Copyright (C) 2023  Antonios Hazim
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

import // Importing necessary libraries and components
    androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.machiav3lli.backup.R
import com.machiav3lli.backup.Sheets
import com.machiav3lli.backup.Sheets.HelpSheet
import com.machiav3lli.backup.Sheets.Sheet
import com.machiav3lli.backup.ui.compose.icons.Phosphor
import com.machiav3lli.backup.ui.compose.icons.phosphor.Info
import com.machiav3lli.backup.ui.compose.item.RoundButton
import com.machiav3lli.backup.ui.compose.item.TopBar
import com.machiav3lli.backup.ui.compose.navigation.NavItem
import com.machiav3lli.backup.ui.compose.navigation.NavHost
import com.machiav3lli.backup.ui.compose.navigation.PagerNavBar
import com.machiav3lli.backup.ui.compose.navigation.SlidePager
import com.topjohnwu.superuser.Shell
import kotlinx.coroutines.launch


// Function to display the Preferences page
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PrefsPage(
    navController: NavHostController, // Navigation controller for navigating between screens
) {
    val context = LocalContext.current // Current context for accessing system services
    val scope = rememberCoroutineScope() // Coroutine scope for launching coroutines
    val pages = listOf( // List of preference pages
        NavItem.UserPrefs,
        NavItem.ServicePrefs,
        NavItem.AdvancedPrefs,
        NavItem.ToolsPrefs,
    )
    val pagerState = rememberPagerState(pageCount = { pages.size }) // Pager state for managing the pager
    val currentPage by remember(pagerState.currentPage) { mutableStateOf(pages[pagerState.currentPage]) } // Current page
    var showHelpSheet by remember { mutableStateOf(false) } // State for showing the help sheet
    val helpSheetState = rememberModalBottomSheetState(true) // Modal bottom sheet state for the help sheet

    // Request root access using Superuser
    Shell.getShell()

    // Back handler to navigate up when the back button is pressed
    BackHandler {
        navController.navigateUp()
    }

    // Scaffold for displaying the app bar, navigation bar, and main content
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        contentColor = MaterialTheme.colorScheme.onSurface,
        topBar = {
            TopBar(
                title = stringResource(id = currentPage.title), // Title of the app bar
                navigationIcon = {
                    RoundButton(
                        icon = Phosphor.Info, // Help button icon
                        description = stringResource(id = R.string.help), // Help button description
                        onClick = {
                            showHelpSheet = true // Show the help sheet when the help button is clicked
                        }
                    )
                }
            )
        },
        bottomBar = {
            PagerNavBar(
                pageItems = pages, // List of preference pages
                pagerState = pagerState, // Pager state
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp) // Modifier for adding padding
            )
        }
    ) { paddingValues -> // Content padding values
        SlidePager(
            modifier = Modifier.padding(paddingValues), // Modifier for adding padding
            pagerState = pagerState, // Pager state
            pageItems = pages, // List of preference pages
            navController = navController // Navigation controller for navigating between screens
        )

        // Display the help sheet when showHelpSheet is true
        if (showHelpSheet) {
            Sheet(
                sheetState = helpSheetState, // Modal bottom sheet state
                onDismissRequest = {
                    scope.launch { helpSheetState.hide() } // Hide the help sheet when the back button is pressed
                    showHelpSheet
