package com.example.guarderia.ui.utils


import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.guarderia.domain.viewmodel.auth.AuthViewModel
import com.example.guarderia.ui.routes.Routes
import kotlinx.coroutines.runBlocking

@Composable
fun GuarderiaAppBar(
    modifier: Modifier = Modifier,
    currentRoute: Routes,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    authViewModel: AuthViewModel,
    isLogoutEnable: Boolean
) {
    TopAppBar(title = { Text(text = stringResource(id = currentRoute.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        },
        actions = {
            if (isLogoutEnable) {
                IconButton(onClick = {
                    runBlocking {
                        authViewModel.logout(null)
                    }
                }) {
                    Icon(Icons.Filled.Logout, contentDescription = "")
                }
            }
        }
    )
}