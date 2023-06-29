package com.example.guarderia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guarderia.R
import com.example.guarderia.domain.viewmodel.announce.AnnounceStatus
import com.example.guarderia.domain.viewmodel.announce.AnnounceViewModel
import com.example.guarderia.model.Announcement

@Composable
fun ViewNoticeScreen(modifier: Modifier=Modifier,id: String) {
    val announceViewModel: AnnounceViewModel =
        viewModel(factory = AnnounceViewModel.createFactory(id))
    val announceUiState by announceViewModel.uiState.collectAsState()

    when (announceUiState.announceStatus) {
        AnnounceStatus.Success -> AnnounceView(modifier,announcement = announceUiState.announcement!!)
        AnnounceStatus.Error -> AnnouncementError(errorMessage = announceUiState.errorMessage)
        else -> {
            CheckingScreen()
        }
    }
}

@Composable
fun AnnouncementError(errorMessage: String) {
    Text(text = "Lo siento, ocurrio un error:${errorMessage}")
}

@Composable
fun AnnounceView(modifier: Modifier,announcement: Announcement) {
    Column(modifier.padding(10.dp)){
        Text(text = announcement.title, fontSize = 20.sp, fontWeight = FontWeight(700))
        ImportanceLabel(announcement.importance)
        Text(text = announcement.date, fontSize = 15.sp)
        Text(text = announcement.body, fontSize = 15.sp)
    }
}

@Composable
fun ImportanceLabel(importance: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        when (importance) {
            4 -> {
                Icon(Icons.Filled.Flag, contentDescription = "", tint = Color.Red)
                Text(
                    text = stringResource(id = R.string.announcementImportance4),
                    fontSize = 20.sp,
                    color = Color.Red
                )
            }

            3 -> {
                Icon(
                    Icons.Filled.Flag,
                    contentDescription = "",
                    tint = colorResource(id = R.color.mediumImportance)
                )
                Text(
                    text = stringResource(id = R.string.announcementImportance3),
                    fontSize = 20.sp,
                    color = colorResource(
                        id = R.color.mediumImportance
                    )
                )
            }

            2 -> {
                Icon(
                    Icons.Filled.Flag,
                    contentDescription = "",
                    tint = colorResource(id = R.color.lowImportance)
                )
                Text(
                    text = stringResource(id = R.string.announcementImportance2),
                    fontSize = 20.sp,
                    color = colorResource(
                        id = R.color.lowImportance
                    )
                )
            }

            1 -> {
                Icon(
                    Icons.Filled.Flag,
                    contentDescription = "",
                    tint = colorResource(id = R.color.noImportance)
                )
                Text(
                    text = stringResource(id = R.string.announcementImportance1),
                    fontSize = 20.sp,
                    color = colorResource(
                        id = R.color.noImportance
                    )
                )
            }

            else -> {
                Icon(Icons.Outlined.Flag, contentDescription = "")
                Text(text = stringResource(id = R.string.announcementImportance0), fontSize = 20.sp)
            }
        }

    }



}



