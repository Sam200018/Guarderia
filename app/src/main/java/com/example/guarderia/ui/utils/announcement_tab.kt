package com.example.guarderia.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.guarderia.R
import com.example.guarderia.model.Announcement
import com.example.guarderia.ui.routes.Routes


@Composable
fun AnnouncementTab(
    modifier: Modifier,
    announcement: Announcement,
    navController: NavHostController,
    roleId: Int
) {

    Card(
        modifier = modifier
            .height(87.dp)
    ) {
        Button(
            onClick = { navController.navigate(Routes.ViewNotice.route+"/${announcement.id}") },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ImportanceIcon(modifier = modifier, importance = announcement.importance)
                Column(modifier.width(240.dp)) {
                    Text(text = announcement.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(text = announcement.body, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                if (roleId==1){
                    DotsButton(announcement.id)}
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
            4 -> Icon(Icons.Filled.Flag, contentDescription = "", tint = Color.Red)
            3 -> Icon(
                Icons.Filled.Flag,
                contentDescription = "",
                tint = colorResource(id = R.color.mediumImportance)
            )

            2 -> Icon(
                Icons.Filled.Flag,
                contentDescription = "",
                tint = colorResource(id = R.color.lowImportance)
            )

            1 -> Icon(
                Icons.Filled.Flag,
                contentDescription = "",
                tint = colorResource(id = R.color.noImportance)
            )

            else -> Icon(Icons.Outlined.Flag, contentDescription = "")
        }

    }

}

@Composable
fun DotsButton(announcementId: Int) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(Icons.Default.MoreVert, contentDescription = "")
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(onClick = {

        }) {
            Row() {
                Icon(Icons.Filled.Edit, contentDescription = "")
                Text(text = "Editar ")
            }
        }
        DropdownMenuItem(onClick = { }) {
            Row() {
                Icon(Icons.Filled.Delete, contentDescription = "")
                Text(text = "Eliminar ")
            }
        }
    }
}