package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.guarderia.R
import com.example.guarderia.model.Announcement



@Composable
fun AnnouncementTab(modifier: Modifier, announcement: Announcement) {

    Card(modifier = modifier.height(87.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImportanceIcon(modifier = modifier, importance = announcement.importance)
            Column(modifier.width(450.dp)) {
                Text(text = announcement.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = announcement.body, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
fun ImportanceIcon(modifier: Modifier, importance: Int) {
    IconButton(
        enabled = false,
        onClick = { }) {
        when (importance) {
            1 -> Icon(Icons.Filled.Flag, contentDescription = "", tint = Color.Red)
            2 -> Icon(
                Icons.Filled.Flag,
                contentDescription = "",
                tint = colorResource(id = R.color.mediumImportance)
            )

            3 -> Icon(
                Icons.Filled.Flag,
                contentDescription = "",
                tint = colorResource(id = R.color.lowImportance)
            )

            4 -> Icon(
                Icons.Filled.Flag,
                contentDescription = "",
                tint = colorResource(id = R.color.noImportance)
            )

            else -> Icon(Icons.Outlined.Flag, contentDescription = "")
        }

    }

}