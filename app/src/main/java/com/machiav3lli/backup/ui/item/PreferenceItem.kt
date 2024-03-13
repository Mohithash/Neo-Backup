// Import necessary classes and functions

// Base Pref class for all preference types
class Pref(
    // Properties for the preference, such as key, default value, title, etc.
    var key: String,
    val private: Boolean = false,
    val defaultValue: Any? = null,
    @StringRes val titleId: Int,
    val summary: String? = null,
    @StringRes val summaryId: Int,
    val icon: ImageVector? = null,
    val iconTint: Color?,
    val enableIf: (() -> Boolean)? = null,
    val onChanged: ((Pref) -> Unit)? = null,
    var group: String = "",
) {
    // Companion object for utility functions and constants
    companion object {
        // Map to store preference groups and their preferences
        val prefGroups: MutableMap<String, MutableList<Pref>> = mutableMapOf()
        // Computed property to get all preferences
        val prefs get() = prefGroups.values.flatten()
        // Counter for locked actions
        var lockedActions = 0

        // Map to store preference change listeners
        val prefChangeListeners = mutableStateMapOf<Pref, (pref: Pref) -> Unit>()
        // Function to handle preference changes
        private fun onPrefChange(name: String) {
            // Iterate through all preference change listeners
            prefChangeListeners.forEach { (pref, listener) ->
                // Call the listener with the updated preference
                listener(pref)
            }
            // Find the preference with the given name and call its onChanged function
            prefs.find { it.key == name }?.let { pref ->
                pref.onChanged?.let { onChanged ->
                    MainScope().launch {
                        while (lockedActions > 0)
                            delay(500)
                        onChanged(pref)
                    }
                }
            }
        }

        // Function to get the SharedPreferences object
        private fun getPrefs(private: Boolean = false) =
            if (private)
                OABX.context.getPrivateSharedPrefs()
            else
                OABX.context.getDefaultSharedPreferences()

        // Functions to get and set preference flags, strings, and integers
        fun prefFlag(name: String, default: Boolean, private: Boolean = false) =
            try {
                getPrefs(private).getBoolean(name, default)
            } catch (e: Throwable) {
                default
            }

        fun setPrefFlag(name: String, value: Boolean, private: Boolean = false) {
            if (!private) tracePrefs { "set pref $name = $value" }
            getPrefs(private).edit().putBoolean(name, value).apply()
            onPrefChange(name)
        }

        fun prefString(name: String, default: String, private: Boolean = false) =
            try {
                getPrefs(private).getString(name, default) ?: default
            } catch (e: Throwable) {
                default
            }

        fun setPrefString(name: String, value: String, private: Boolean = false) {
            if (!private) tracePrefs { "set pref $name = '$value'" }
            getPrefs(private).edit().putString(name, value).apply()
            onPrefChange(name)
        }

        fun prefInt(name: String, default: Int, private: Boolean = false) =
            try {
                getPrefs(private).getInt(name, default)
            } catch (e: Throwable) {
                default
            }

        fun setPrefInt(name: String, value: Int, private: Boolean = false) {
            if (!private) tracePrefs { "set pref $name = $value" }
            getPrefs(private).edit().putInt(name, value).apply()
            onPrefChange(name)
        }

        // Functions to escape and unescape strings
        private val toBeEscaped =
            Regex("""[\\"\n\r\t]""")      // blacklist, only escape those that are necessary

        private val toBeUnescaped =
            Regex("""\\(.)""")      // blacklist, only escape those that are necessary

        fun escape(value: String): String {
            return value.replace(toBeEscaped) {
                when (it.value) {
                    "\n" -> "\\n"
                    "\r" -> "\\r"
                    "\t" -> "\\t"
                    else -> "\\${it.value}"
                }
            }
        }

        fun unescape(value: String): String {
            return value.replace(toBeUnescaped) { match ->
                match.groupValues[1].let {
                    when (it) {
                        "n"  -> "\n"
                        "r"  -> "\r"
                        "t"  -> "\t"
                        else -> it
                    }
                }
            }
        }

        // Functions to convert preferences to and from a simple format
        fun toSimpleFormat(entries: Map<String, Any>): String {
            return entries.toSortedMap().mapNotNull {
                when (it.value) {
                    is String  -> it.key to "\"" + escape(it.value as String) + "\""
                    is Int     -> it.key to (it.value as Int).toString()
                    is Boolean -> it.key to (it.value as Boolean).toString()
                    else       -> null
                }
            }.joinToString("\n") { (key, value) ->
                "$key: $value"
            }
        }

        fun fromSimpleFormat(serialized: String): Map<String, Any> {
            val map = mutableMapOf<String, Any>()
            serialized.lineSequence().forEach {
                var (key, value) = it.split(":", limit = 2)
                value = value.trim()
                runCatching {
                    when {
                        value.startsWith('"')
                                && value.endsWith('"') -> {
                           
