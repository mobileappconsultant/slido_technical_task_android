package com.example.usersdir.ui.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.usersdir.domain.model.User

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersList(users: List<User>, onDeleteUser: (User) -> Unit) {
    LazyColumn {
        item {
            Text("Users Result")
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(
            users
        ) { user ->
            UserListItem(user, onDeleteUser)
            Spacer(Modifier.height(16.dp))
        }
    }
}
