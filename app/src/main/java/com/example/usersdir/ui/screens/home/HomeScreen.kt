package com.example.usersdir.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.usersdir.R
import com.example.usersdir.domain.model.User
import com.example.usersdir.ui.composable.ErrorPage
import com.example.usersdir.ui.composable.FullScreenProgress
import com.example.usersdir.ui.composable.UsersList

@Composable
fun HomeScreen(onCreateUserClicked: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = stringResource(id = R.string.app_name),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onCreateUserClicked() }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add User")
            }
        }
    ) {
        val homeViewModel = hiltViewModel<HomeViewModel>()
        val state by homeViewModel.uiState.collectAsState()
        val user = remember {
            mutableStateOf<User?>(null)
        }
        var showDialog by remember {
            mutableStateOf(false)
        }

        Column(Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))

            when (state) {
                is HomeViewModel.DataState.Failure -> {
                    ErrorPage(message = (state as HomeViewModel.DataState.Failure).message) {
                        homeViewModel.getUsers()
                    }
                }
                HomeViewModel.DataState.Loading -> {
                    FullScreenProgress()
                }
                is HomeViewModel.DataState.Success -> {
                    UsersList(
                        users = (state as HomeViewModel.DataState.Success).users,
                        onDeleteUser = {
                            user.value = it
                            showDialog = true
                        }
                    )
                }
                else -> {
                    // No OP
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Delete User")
                },
                text = {
                    Text(text = "Are you sure you want to delete this user?")
                },
                buttons = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { showDialog = false }
                        ) {
                            Text("No")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextButton(
                            onClick = {
                                user.value?.let { it -> homeViewModel.deleteUser(it) }
                                showDialog = false
                            }
                        ) {
                            Text("Yes")
                        }
                    }
                }
            )
        }
    }
}
