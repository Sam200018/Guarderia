package com.example.guarderia.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guarderia.R
import com.example.guarderia.domain.viewmodel.announcement.AnnouncementViewModel
import com.example.guarderia.domain.viewmodel.home.HomeViewModel
import com.example.guarderia.ui.utils.SelectedDate
import kotlinx.coroutines.runBlocking

@Composable
fun EditAnnouncement(
    modifier: Modifier = Modifier,
    id: String,
    navController: NavController,
    homeViewModel: HomeViewModel,
    announcementViewModel: AnnouncementViewModel,
) {
    val scrollState = rememberScrollState()

    val uiState by announcementViewModel.uiState.collectAsState()
    val mContext = LocalContext.current

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 10.dp)
    ) {
        Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TitleInput(modifier = modifier, title = announcementViewModel.titleInput) {
                announcementViewModel.onTitleChange(it)
            }

            ImportanceInput(
                modifier = modifier,
                importance = announcementViewModel.importanceInput,
            ) {
                announcementViewModel.onImportanceChange(it)
            }
        }
        Box(modifier.height(15.dp))
        SelectedDate(context = mContext, date = announcementViewModel.dueDateInput, setDate = {
            announcementViewModel.onDueDateChange(it)
        })
        Box(modifier.height(15.dp))
        DescriptionInput(
            modifier = modifier,
            description = announcementViewModel.descriptionInput
        ) {
            announcementViewModel.onDescriptionChange(it)
        }
        Box(modifier.height(30.dp))
        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

            Button(
                enabled = isSendButtonEnable(uiState, announcementViewModel),
                onClick = {
                    runBlocking {
                        announcementViewModel.editAnnouncement(id)
                        navController.popBackStack()
                        homeViewModel.checkingRole()
                    }
                },
                modifier = modifier.height(50.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(
                        id = R.color.green_button
                    ),
                    disabledBackgroundColor = colorResource(
                        id = R.color.green_button_disable
                    )
                )
            ) {
                Text(text = stringResource(id = R.string.editAnnouncement))
            }
        }
    }
}
