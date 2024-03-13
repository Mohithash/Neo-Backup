// Import section: importing necessary classes and packages

// Column composable to hold the preference groups in a vertical arrangement
@Composable
fun DevPrefGroups() {
    val preferenceGroups = listOf(
        "advanced users (for those who know)" to Pref.prefGroups["dev-adv"] ?: listOf(),
        "workarounds (hacks)" to Pref.prefGroups["dev-hack"] ?: listOf(),
        "logging" to Pref.prefGroups["dev-log"] ?: listOf(),
        "tracing" to Pref.prefGroups["dev-trace"] ?: listOf(),
        "file handling" to Pref.prefGroups["dev-file"] ?: listOf(),
        "faking (simulated actions)" to Pref.prefGroups["dev-fake"] ?: listOf(),
        "new experimental (for devs)" to Pref.prefGroups["dev-new"] ?: listOf(),
        "alternates (for devs to compare)" to Pref.prefGroups["dev-alt"] ?: listOf()
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        preferenceGroups.forEach { (heading, prefs) ->
            displayPrefsGroupCollapsed(heading, prefs)
        }
    }
}

// Composable function to display the collapsed preference group
@Composable
fun displayPrefsGroupCollapsed(heading: String, prefs: List<Pref>) {
    PrefsGroupCollapsed(prefs = prefs, heading = heading)
}

// Composable function to display the advanced preferences page
@Composable
fun AdvancedPrefsPage() {
    val context = LocalContext.current
    val (expanded, expand) = remember { mutableStateOf(false) }
    val prefs = Pref.prefGroups["adv"] ?: listOf()

    BusyBackground(
        modifier = Modifier
            .blockBorder()
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                PrefsGroup(prefs = prefs) { pref ->
                    if (pref == pref_enableSpecialBackups) {
                        //TODO hg4
                    }
                }
            }
        }
    }
}
