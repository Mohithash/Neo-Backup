package com.machiav3lli.backup.ui.compose.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.machiav3lli.backup.ui.item.Link

@Composable // Declares this function as a Composable for Jetpack Compose
fun LinkItem(item: Link, onClick: (String) -> Unit) { // Receives a Link object and a function to handle clicks
    CardSubRow(
        text = stringResource(id = item.nameId), // Sets the text using the nameId from the Link object
        icon = item.icon, // Sets the icon using the icon from the Link object
        iconColor = colorResource(id = item.iconColorId), // Sets the icon color using the iconColorId from the Link object
        modifier = Modifier.fillMaxWidth(), // Makes the row fill the maximum width
        onClick = { onClick(item.uri) } // Handles clicks by calling the provided onClick function with the uri from the Link object
    )
}
