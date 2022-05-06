package com.example.usersdir.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.xenbank.androidapp.ui.composable.FormState

@ExperimentalComposeUiApi
@Composable
fun Form(state: FormState, fields: List<Field>) {
    state.fields = fields

    Column {
        fields.forEach {
            it.Content()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
