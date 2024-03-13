package research

import android.content.res.Configuration
import androidx.test.platform.app.InstrumentationRegistry
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.PREFS_LANGUAGES_SYSTEM
import com.machiav3lli.backup.utils.getLocaleOfCode
import org.junit.Test
import java.util.*

class Try_locales {

    // This test function is used to test the language change functionality
    @Test
    fun test_language() {

        // Get the application configuration
        val appConfig = OABX.context.resources.configuration

        // Get the instrumentation context and its configuration
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = context.resources.configuration

        // A function to print the current locales
        fun showLocales() {
            println("Locale.getDefault: ${Locale.getDefault()}")
            println("appLocale: ${appConfig.locales[0]}")
            println("locale: ${config.locales[0]}")
        }

        var sysLocale: Locale? = null // A variable to store the system locale

        // A function to set the language
        fun setLanguage(language: String): Configuration {

            val config = context.resources.configuration // Get the configuration

            println("----- setLanguage '$language' $config") // Print the configuration

            if (sysLocale == null)
                sysLocale = config.locales[0] // If sysLocale is null, set it to the current locale

            var setLocalCode = language // Set the local code to the input language
            if (setLocalCode == PREFS_LANGUAGES_SYSTEM) { // If the language is system language
                setLocalCode = sysLocale?.language ?: Locale.US.language // Set the local code to the system language or US language if sysLocale is null
            }

            val newLocale = context.getLocaleOfCode(setLocalCode) // Get the new locale
            config.setLocale(newLocale) // Set the new locale
            Locale.setDefault(newLocale) // Set the default locale

            return config // Return the configuration
        }

        showLocales() // Print the current locales

        setLanguage("de_DE") // Set the language to German (Germany)

        showLocales() // Print the current locales

        setLanguage(PREFS_LANGUAGES_SYSTEM) // Set the language to the system language

        showLocales() // Print the current locales

        setLanguage("en_US") // Set the language to English (US)

        showLocales() // Print the current locales

        setLanguage("en_DE") // Set the language to English (Germany)

