package com.example.guarderia.ui.utils

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//TODO: Add all the titles of the bottom bar
val items = listOf("Songs", "Artists", "Playlists")

@Composable
fun GuarderiaBottomNav(
    modifer: Modifier = Modifier
) {
    BottomNavigation(modifier = modifer) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = index == 0,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = null
                    )
                },
                label = { Text(text = item) }
            )
        }
    }
}