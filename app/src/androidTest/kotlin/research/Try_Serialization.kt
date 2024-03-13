package research

import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.SerializersModule
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// Type alias for a map with string keys and 'AnyType' values.
typealias MapAny = Map<String, AnyType>

/**
 * A serializer for a map with string keys and 'AnyType' values.
 * This serializer supports encoding and decoding the map to/from JSON.
 */
@Serializer(forClass = Map::class)
object MapAnySerializer : KSerializer<Map<String, AnyType>> {
    //... (Serializer implementation)
}

/**
 * A type alias for a map with string keys and 'Primitive' values.
 */
typealias MapPrimitive = Map<String, Primitive>

/**
 * A sealed class representing a primitive value.
 * It can be an integer, a boolean, or a string.
 */
sealed class Primitive {
    //... (Primitive class implementation)
}

/**
 * A serializer for the 'Primitive' sealed class.
 */
object PrimitiveSerializer : KSerializer<Primitive> {
    //... (Serializer implementation)
}

/**
 * A serializers module for the 'Primitive' sealed class.
 */
val mapPrimitiveSerializer = MapSerializer(String.serializer(), PrimitiveSerializer)

/**
 * A class containing serialization tests.
 */
class Try_Serialization {
    //... (Test class implementation)
}
