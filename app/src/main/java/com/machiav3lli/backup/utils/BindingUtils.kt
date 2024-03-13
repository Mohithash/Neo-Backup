/**
 * Returns a formatted string representing the date and/or time of the given LocalDateTime object.
 *
 * @param withTime A boolean flag indicating whether to include the time in the formatted string.
 * @return A formatted string representing the date and/or time of the given LocalDateTime object.
 */
fun LocalDateTime.getFormattedDate(withTime: Boolean): String? {
    // Create a DateTimeFormatter object based on the input flag
    val dtf = if (withTime) DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    else DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    
    // Format the LocalDateTime object using the DateTimeFormatter object and return the result
    return format(dtf)
}
