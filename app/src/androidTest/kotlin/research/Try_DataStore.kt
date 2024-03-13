package research

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException

/**
 * Interface for Preference DataStore operations.
 */
interface IPreferenceDataStore {
    suspend fun <T> getPrefFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> // Returns a flow of data for the given key.
    suspend fun <T> getPref(key: Preferences.Key<T>, defaultValue: T): T // Returns the current value for the given key.
    suspend fun <T> putPref(key: Preferences.Key<T>, value: T) // Saves the given value for the given key.
    suspend fun <T> removePref(key: Preferences.Key<T>) // Removes the value for the given key.
    suspend fun <T> clearPrefs() // Clears all saved preferences.
}

/**
 * Extension function to get the DataStore instance.
 */
private val Context.dataStore by preferencesDataStore(
    name = "settings",
)

/**
 * Implementation of the IPreferenceDataStore interface.
 */
class PreferencesDataStore(): IPreferenceDataStore {

    val store = context.dataStore // DataStore instance.

    companion object {
        val intKey = intPreferencesKey("int") // Key for integer preference.
        val boolKey = booleanPreferencesKey("bool") // Key for boolean preference.
        val strKey = stringPreferencesKey("str") // Key for string preference.
    }

    /**
     * Returns a flow of data for the given key.
     */
    override suspend fun <T> getPrefFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        store.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences()) // If there's an IOException, emit empty preferences.
            } else {
                throw exception // Otherwise, rethrow the exception.
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue // Get the value for the given key or the default value.
            result
        }

    /**
     * Returns the current value for the given key.
     */
    override suspend fun <T> getPref(key: Preferences.Key<T>, defaultValue: T): T =
        store.data.first()[key] ?: defaultValue // Get the value for the given key or the default value.

    /**
     * Saves the given value for the given key.
     */
    override suspend fun <T> putPref(key: Preferences.Key<T>, value: T) {
        store.edit { preferences ->
            preferences[key] = value // Edit the preferences and save the given value for the given key.
        }
    }

    /**
     * Removes the value for the given key.
     */
    override suspend fun <T> removePref(key: Preferences.Key<T>) {
        store.edit { preferences ->
            preferences.remove(key) // Edit the preferences and remove the value for the given key.
        }
    }

    /**
     * Clears all saved preferences.
     */
    override suspend fun <T> clearPrefs() {
        store.edit { preferences ->
            preferences.clear() // Edit the preferences and clear all saved values.
        }
    }
}

/**
 * Base class for preference data classes.
 */
open class DPref() {
}

/**
 * Data class for integer preferences.
 */
class DPrefInt(name: String, var default: Int): DPref() {

    val key = intPreferencesKey(name) // Preference key.

    /**
     * Getter for the current preference value.
     */
    var value
        get() = runBlocking { prefStore.getPref(key, default) } // Get the value for the given key.
        set(value) = runBlocking { prefStore.putPref(key, value) } // Save the given value for the given key.
}

/**
 * Data class for boolean preferences.
 */
class DPrefBoolean(name: String, var default: Boolean): DPref() {

    val key = booleanPreferencesKey(name) // Preference key.

    /**
     * Getter for the current preference value.
     */
    var value
        get() = runBlocking { prefStore.getPref(key, default) } // Get the value for the given key.
        set(value) = runBlocking { prefStore.putPref(key, value) } // Save the given value for the given key.
}

/**
 * Data class for string preferences.
 */
class DPrefString(name: String, var default: String): DPref() {

    val key = stringPreferencesKey(name) // Preference key.

    /**
     * Getter for the current preference value.
     */
    var value
        get() = runBlocking { prefStore.getPref(key, default) } // Get the value for the given key.
        set(value) = runBlocking { prefStore.putPref(key, value) } // Save the given value for the given key.
}

/**
 * Test class for DataStore operations.
 */
class Try_DataStore {

    /**
     * Test method for DataStore operations.
     */
    @Test
    fun test_Prefs() {

        val int = DPrefInt("int", 123) // Create a new integer preference.
       
