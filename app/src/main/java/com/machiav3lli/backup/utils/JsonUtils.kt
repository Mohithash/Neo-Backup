/*
 * OAndBackupX: open-source apps backup and restore app.
 * Copyright (C) 2020  Antonios Hazim
 *
 * This file contains utility functions and custom serializers for the app.
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

package com.machiav3lli.backup.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Custom serializer for [LocalDateTime] using the ISO_LOCAL_DATE_TIME format.
 * This serializer is used to serialize and deserialize LocalDateTime objects to and from strings.
 *
 * @ExperimentalSerializationApi indicates that this class uses experimental features of the
 * serialization library.
 */
@ExperimentalSerializationApi
@Serializer(forClass = LocalDateTime::class)
object LocalDateTimeSerializer : KSerializer<LocalDateTime> {

    /**
     * The serial descriptor for this serializer, which is a string primitive.
     */
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTimeSerializer", PrimitiveKind.STRING)

    /**
     * Serializes the given LocalDateTime value to a string in the ISO_LOCAL_DATE_TIME format.
     */
    override fun serialize(encoder: Encoder, value: LocalDateTime) =
        encoder.encodeString(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(value))

    /**
     * Deserializes a LocalDateTime value from a string in the ISO_LOCAL_DATE_TIME format.
     */
    override fun deserialize(decoder: Decoder): LocalDateTime =
        LocalDateTime.parse(decoder.decodeString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}

/**
 * Custom serializer for [Uri] using the toString method.
 * This serializer is used to serialize and deserialize Uri objects to and from strings.
 *
 * @ExperimentalSerializationApi indicates that this class uses experimental features of the
 * serialization library.
 */
@ExperimentalSerializationApi
@Serializer(forClass = Uri::class)
object UriSerializer : KSerializer<Uri> {

    /**
     * The serial descriptor for this serializer, which is a string primitive.
     */
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("UriSerializer", PrimitiveKind.STRING)

    /**
     * Serializes the given Uri value to a string using the toString method.
     */
    override fun serialize(encoder: Encoder, value: Uri) =
        encoder.encodeString(value.toString())

    /**
     * Deserializes a Uri value from a string using the Uri.parse method.
     */
    override fun deserialize(decoder: Decoder): Uri = Uri.parse(decoder.decodeString())
}
