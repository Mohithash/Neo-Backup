package com.machiav3lli.backup.ui.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.pages.LockPage
import com.machiav3lli.backup.pages.MainPage
import com.machiav3lli.backup.pages.PermissionsPage
import com.machiav3lli.backup.pages.PrefsPage
import com.machiav3lli.backup.pages.WelcomePage
import com.machiav3lli.backup.preferences.ExportsPage
import com.machiav3lli.backup.preferences.LogsPage
import com.machiav3lli.backup.preferences.TerminalPage
import com.machiav3lli.backup.preferences.persist_beenWelcomed

// Defines the MainNavHost composable function which takes a modifier and a NavHostController
// as parameters. This function initializes a NavHost with a startDestination based on the
// value of persist_beenWelcomed.value.
@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (persist_beenWelcomed.value) NavItem.Permissions.destination
        else NavItem.Welcome.destination
    ) {
        // Adds a slide-in animation to the LockPage composable.
        slideInComposable(NavItem.Lock.destination) {
            LockPage { OABX.main?.resumeMain() }
        }
        // Adds a slide-in animation to the WelcomePage composable.
        slideInComposable(NavItem.Welcome.destination) {
            WelcomePage()
        }
        // Adds a slide-in animation to the PermissionsPage composable.
        slideInComposable(route = NavItem.Permissions.destination) {
            PermissionsPage()
        }
        // Adds a slide-in animation to the MainPage composable.
        slideInComposable(NavItem.Main.destination) {
            MainPage(
                navController = navController
            )
        }
        // Adds a slide-in animation to the PrefsPage composable.
        slideInComposable(NavItem.Settings.destination) {
            PrefsPage(
                navController = navController
            )
        }
        // Adds a slide-in animation to the ExportsPage composable.
        slideInComposable(NavItem.Exports.destination) {
            OABX.main?.exportsViewModel?.let { viewModel ->
                ExportsPage(viewModel)
            }
        }
        // Adds a slide-in animation to the LogsPage composable.
        slideInComposable(NavItem.Logs.destination) {
            OABX.main?.logsViewModel?.let { viewModel ->
                LogsPage(viewModel)
            }
        }
        // Adds a slide-in animation to the TerminalPage composable.
        slideInComposable(NavItem.Terminal.destination) {
            TerminalPage()
        }
    }
}

// Defines a function to add a slide-in animation to a composable in the NavGraphBuilder.
fun NavGraphBuilder.slideInComposable(
    route: String,
    composable: @Composable (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit),
) {
    // Adds the composable with custom enter and exit transitions.
    composable(
        route,
        enterTransition = { slideInHorizontally { width -> width } },
        exitTransition = { slideOutHorizontally { width -> -width } },
        popEnterTransition = { slideInHorizontally { width -> -width } },
        popExitTransition = { slideOutHorizontally { width -> width } },
    ) {
        // Calls the composable function.
        composable(it)
    }
}

// Defines a function to add a slide-down animation to a composable in the NavGraphBuilder.
fun NavGraphBuilder.slideDownComposable(
    route: String,
    composable: @Composable (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit),
) {
    // Adds the composable with custom enter and exit transitions.
    composable(
        route,
        enterTransition = { slideInVertically { height -> -height } + fadeIn() },
        exitTransition = { slideOutVertically { height -> height } + fadeOut() }
    ) {
        // Calls the composable function.
        composable(it)
    }
}

// Defines a function to add a fade-in animation to a composable in the NavGraphBuilder.
fun NavGraphBuilder.fadeComposable(
    route: String,
    composable: @Composable (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit),
) {
    // Adds the composable with custom enter and exit transitions.
    composable(
        route,
        enterTransition = { fadeIn(initialAlpha = 0.1f) },
        exitTransition = { fadeOut() }
    ) {
        // Calls the composable function.
        composable(it)
    }
}

// Defines a function for the NavHostController to clear the back stack.
fun NavHostController.clearBackStack() {
    // Clears the back stack by pop
