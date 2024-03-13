package tests

import androidx.test.platform.app.InstrumentationRegistry
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.ui.item.Pref.Companion.escape
import com.machiav3lli.backup.ui.item.Pref.Companion.fromSimpleFormat
import com.machiav3lli.backup.ui.item.Pref.Companion.toSimpleFormat
import com.machiav3lli.backup.ui.item.Pref.Companion.unescape
import junit.framework.TestCase.assertEquals
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.TestOnly
import org.junit.Test

class Test_Serialization {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val strings = mapOf(
        "special" to
                """test  , \ | $ & " ' ` [ ] ( ) { } = : ; ? < ~ > - + ! % ^ # * """,
        "escaped" to
                "\u0007\b\u000c\n\r\t\u0012\u001b🎃\uD83C\uDF83",
        "0..32" to
                (0..32).map { it.toChar() }
                    .filter { c -> c.code != 0 && c !in "\n\r" }
                    .joinToString(""),
        "33..127" to
                (33..127).map { it.toChar() }
                    .filter { c -> !(c.isLetterOrDigit() || c == '/') }
                    .joinToString(""),
        "128+(0..32)" to
                (0..32).map { (128 + it).toChar() }
                    .filter { c ->
                        c.code != 0 &&
                                c.code != 173 &&
                                (c.code and 127).toChar() !in "\t\n\r "
                    }
                    .joinToString(""),
        "128+(33..127)" to
                (33..127).map { (128 + it).toChar() }
                    .joinToString(""),
    )

    private val aMap = mapOf<String, Any>(
        "int" to 123,
        "boolean" to false,
    ) + strings

    @Serializable
    data class AClass(val int: Int = 123, val flag: Boolean = false, val str: String = "abc")

    private val aObj = AClass(int = 456, flag = true, str = "str")

    @TestOnly
    internal fun test_esc(value: String) {
        val escaped = escape(value)
        println("Escaped: '$escaped'")
        val unescaped = unescape(escaped)
        assertEquals(value, unescaped)
    }

    @Test
    fun test_simple_escape() {
        strings.forEach { name, value ->
            test_esc(value)
        }
    }

    @Test
    fun test_simple() {
        val serialized = toSimpleFormat(aMap)
        println("Simple format:\n$serialized\n")
        val deserialized = fromSimpleFormat<Map<String, Any>>(serialized)
        assertEquals(aMap, deserialized)
    }

    @Test
    fun test_json_obj() {
        val serialized = OABX.toSerialized(OABX.JsonPretty, aObj)
        println("JSON format:\n$serialized\n")
        val deserialized = OABX.fromSerialized<AClass>(serialized)
        assertEquals(aObj, deserialized)
    }

    @Test
    fun test_json_map() {
        val serialized = OABX.toSerialized(OABX.JsonPretty, aMap)
        println("JSON format:\n$serialized\n")
        val deserialized = OABX.fromSerialized<Map<String, Any>>(serialized)
        assertEquals(aMap, deserialized)
    }

    @Test
    fun test_yaml_obj() {
        val serialized = OABX.toSerialized(OABX.YamlDefault, aObj)
        println("YAML format:\n$serialized\n")
        val deserialized = OABX.fromSerialized<AClass>(serialized)
        assertEquals(aObj, deserialized)
    }

    @Test
    fun test_yaml_map() {
        val serialized = OABX.toSerialized(OABX.YamlDefault, aMap)
        println("YAML format:\n$serialized\n")
        val deserialized = OABX.fromSerialized<Map<String, Any>>(serialized)
        assertEquals(aMap, deserialized)
    }
}
