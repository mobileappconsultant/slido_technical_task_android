package com.example.usersdir.ui.screens.create

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.usersdir.R
import com.example.usersdir.ui.composable.Field
import com.example.usersdir.ui.composable.Form
import com.example.usersdir.ui.composable.PrimaryButton
import com.example.usersdir.utils.Email
import com.example.usersdir.utils.Required
import com.mobile.xenbank.androidapp.ui.composable.FormState

private const val NAME_FIELD = "Name"
private const val EMAIL_FIELD = "Email"
private const val GENDER_FIELD = "Gender"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateUserScreen(onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = stringResource(id = R.string.create_user),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Icon")
                    }
                }
            )
        }
    ) {
        val createUserViewModel = hiltViewModel<CreateUserViewModel>()
        val event by createUserViewModel.event.collectAsState()
        val context = LocalContext.current
        val isLoading by createUserViewModel.loadingState.collectAsState()

        LaunchedEffect(key1 = event) {
            when (event) {
                is CreateUserViewModel.Event.ShowToast -> {
                    Toast.makeText(
                        context,
                        (event as CreateUserViewModel.Event.ShowToast).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is CreateUserViewModel.Event.Navigate -> {
                    onBackPressed()
                }
                else -> {
                    // No Op
                }
            }
            createUserViewModel.clean()
        }

        val formState by remember { mutableStateOf(FormState()) }

        Column(Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))

            Form(
                state = formState,
                fields = listOf(
                    Field(
                        name = NAME_FIELD, validators = listOf(Required()),
                        label = "",
                        placeholder = stringResource(R.string.first_name),
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text,
                        isPasswordField = false
                    ),
                    Field(
                        name = EMAIL_FIELD, validators = listOf(Required(), Email()),
                        label = "",
                        placeholder = stringResource(id = R.string.email_address),
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email,
                        isPasswordField = false
                    ),
                    Field(
                        name = GENDER_FIELD, validators = listOf(Required()),
                        label = "",
                        placeholder = stringResource(id = R.string.gender),
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text,
                        isPasswordField = false,
                    ),
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (formState.validate()) {
                        createUserViewModel.createUser(
                            email = formState.getData().getOrDefault(EMAIL_FIELD, ""),
                            name = formState.getData().getOrDefault(NAME_FIELD, ""),
                            gender = formState.getData().getOrDefault(GENDER_FIELD, ""),
                            status = "active"
                        )
                    }
                },
                text = "Submit", isLoading = isLoading
            )
        }
    }
}
