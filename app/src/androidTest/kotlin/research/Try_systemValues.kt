package research

import org.junit.Assert.assertEquals
import org.junit.Test
import timber.log.Timber

class SystemValuesTest {

    @Test
    fun testSystemValues() {
        Timber.plant(Timber.DebugTree())

        val systemProperty = "os.name"
        val expectedValue = "Linux"
        val actualValue = System.getProperty(systemProperty)

        Timber.d("System property $systemProperty has value $actualValue")
        assertEquals(expectedValue, actualValue)

        val environmentVariable = "PATH"
        val expectedEnvironmentValue = System.getenv(environmentVariable)
        Timber.d("Environment variable $environmentVariable has value $expectedEnvironmentValue")
    }
}
