package com.example.guarderia.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guarderia.R
import com.example.guarderia.domain.viewmodel.announcement.AnnouncementViewModel
import com.example.guarderia.domain.viewmodel.home.HomeViewModel
import com.example.guarderia.ui.utils.SelectedDate
import kotlinx.coroutines.runBlocking

@Composable
fun AddNotice(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val scrollState = rememberScrollState()
    val announcementViewModel: AnnouncementViewModel =
        viewModel(factory = AnnouncementViewModel.Factory)
    val mContext= LocalContext.current




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
        SelectedDate(context = mContext, date = announcementViewModel.dueDateInput , setDate ={
            announcementViewModel.onDueDateChange(it)
        } )
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
                onClick = {
                    runBlocking {
                        announcementViewModel.sendAnnouncement()
                        navController.popBackStack()
                        homeViewModel.checkingRole()
                    }
                },
                modifier = modifier.height(50.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(
                        id = R.color.green_button
                    )
                )
            ) {
                Text(text = "Enviar anuncio")
            }
        }
    }
}

@Composable
fun TitleInput(modifier: Modifier, title: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = modifier.width(321.dp),
        value = title,
        onValueChange = onTextChange,
        label = {
            Text(text = stringResource(id = R.string.announcementTitle))
        },
    )
}

@Composable
fun ImportanceInput(modifier: Modifier, importance: Int, onInputChange: (Int) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(modifier = modifier.fillMaxWidth(),
        onClick = { expanded = true }) {
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
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(
            onClick = {
                onInputChange(1)
                expanded = false
            }) {
            Row() {
                Icon(Icons.Filled.Flag, contentDescription = "", tint = Color.Red)
                Text(text = "Alta importancia")
            }
        }
        DropdownMenuItem(
            onClick = {
                onInputChange(2)
                expanded = false
            }) {
            Row() {
                Icon(
                    Icons.Filled.Flag,
                    contentDescription = "",
                    tint = colorResource(id = R.color.mediumImportance)
                )
                Text(text = "Media importancia")
            }
        }
        DropdownMenuItem(
            onClick = {
                onInputChange(3)
                expanded = false
            }) {
            Row() {
                Icon(
                    Icons.Filled.Flag,
                    contentDescription = "",
                    tint = colorResource(id = R.color.lowImportance)
                )
                Text(text = "Baja importancia")
            }
        }
        DropdownMenuItem(
            onClick = {
                onInputChange(4)
                expanded = false
            }) {
            Row() {
                Icon(
                    Icons.Filled.Flag,
                    contentDescription = "",
                    tint = colorResource(id = R.color.noImportance)
                )
                Text(text = "Sin importancia")
            }
        }
    }
}

@Composable
fun DescriptionInput(modifier: Modifier, description: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = modifier
            .height(452.dp)
            .fillMaxWidth(),
        value = description,
        onValueChange = onTextChange,
        singleLine = false,
        label = {
            Text(text = stringResource(id = R.string.announcementDescription))
        },
    )
}
