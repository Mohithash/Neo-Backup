// Import section: importing necessary classes and packages

// Package declaration for the preferences related to the dev-adv, dev-file, dev-log, dev-trace, dev-hack, dev-alt, dev-new, and dev-fake preference groups
@Composable
fun DevPrefGroups() {
    // Declaring local variables using remember and mutableStateOf for expanded and expand functions
    val devUserOptions = Pref.prefGroups["dev-adv"] ?: listOf()
    val devFileOptions = Pref.prefGroups["dev-file"] ?: listOf()
    val devLogOptions = Pref.prefGroups["dev-log"] ?: listOf()
    val devTraceOptions = Pref.prefGroups["dev-trace"] ?: listOf()
    val devHackOptions = Pref.prefGroups["dev-hack"] ?: listOf()
    val devAltOptions = Pref.prefGroups["dev-alt"] ?: listOf()
    val devNewOptions = Pref.prefGroups["dev-new"] ?: listOf()
    val devFakeOptions = Pref.prefGroups["dev-fake"] ?: listOf()

    // Column composable to hold the preference groups in a vertical arrangement
    Column(
        // Setting the vertical arrangement to be spaced by 4.dp
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // PrefsGroupCollapsed composable to hold the devUserOptions preference group with a heading
        PrefsGroupCollapsed(
            prefs = devUserOptions,
            heading = "advanced users (for those who know)"
        )
        // PrefsGroupCollapsed composable to hold the devHackOptions preference group with a heading
        PrefsGroupCollapsed(prefs = devHackOptions, heading = "workarounds (hacks)")
        // PrefsGroupCollapsed composable to hold the devLogOptions preference group with a heading
        PrefsGroupCollapsed(prefs = devLogOptions, heading = "logging")
        // PrefsGroupCollapsed composable to hold the devTraceOptions preference group with a heading
        PrefsGroupCollapsed(prefs = devTraceOptions, heading = "tracing")
        // PrefsGroupCollapsed composable to hold the devFileOptions preference group with a heading
        PrefsGroupCollapsed(prefs = devFileOptions, heading = "file handling")
        // PrefsGroupCollapsed composable to hold the devFakeOptions preference group with a heading
        PrefsGroupCollapsed(prefs = devFakeOptions, heading = "faking (simulated actions)")
        // PrefsGroupCollapsed composable to hold the devNewOptions preference group with a heading
        PrefsGroupCollapsed(prefs = devNewOptions, heading = "new experimental (for devs)")
        // PrefsGroupCollapsed composable to hold the devAltOptions preference group with a heading
        PrefsGroupCollapsed(prefs = devAltOptions, heading = "alternates (for devs to compare)")
    }
}

// Composable function to display the advanced preferences page
@Composable
fun AdvancedPrefsPage() {
    // Getting the local context
    val context = LocalContext.current
    // Declaring local variables using remember and mutableStateOf for expanded and expand functions
    val (expanded, expand) = remember { mutableStateOf(false) }

    // List of preferences for the advanced users
    val prefs = Pref.prefGroups["adv"] ?: listOf()

    // BusyBackground composable to display a busy indicator while loading the preferences
    BusyBackground(
        // Setting the modifier for the busy background
        modifier = Modifier
            // Applying the blockBorder modifier
            .blockBorder()
            // Filling the maximum size of the screen
            .fillMaxSize()
    ) {
        // LazyColumn composable to display the preferences in a vertical scrolling list
        LazyColumn(
            // Setting the modifier for the lazy column
            modifier = Modifier.fillMaxSize(),
            // Setting the padding values for the lazy column
            contentPadding = PaddingValues(8.dp),
            // Setting the vertical arrangement to be spaced by 8.dp
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Item composable to display the preferences group
            item {
                // PrefsGroup composable to display the prefs preference group
                PrefsGroup(prefs = prefs) { pref ->
                    // Checking if the pref is pref_enableSpecialBackups
                    if (pref == pref_enableSpecialBackups) {        //TODO hg4
