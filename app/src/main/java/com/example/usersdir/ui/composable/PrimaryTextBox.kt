package com.example.usersdir.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.usersdir.ui.theme.LightGrey

@ExperimentalComposeUiApi
@Composable
fun PrimaryTextBox(
    modifier: Modifier,
    value: String = "",
    placeholder: String,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordField: Boolean = false,
    onTextChanged: (String) -> Unit,
    onKeyboardAction: ((String) -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    val text = remember { mutableStateOf(value) }
    val passwordVisibility = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val visualTransformation = when {
        isPasswordField.not() -> VisualTransformation.None
        passwordVisibility.value -> VisualTransformation.None
        else -> PasswordVisualTransformation()
    }
    Column {
        OutlinedTextField(
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            value = text.value,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Blue,
                unfocusedBorderColor = Color.Transparent,
                backgroundColor = LightGrey,
                textColor = Color.Black
            ),
            isError = isError,
            modifier = modifier,
            label = {
                Text(text = placeholder, color = Gray, fontWeight = FontWeight.Light, fontSize = 14.sp)
            },
            trailingIcon = {
                if (isPasswordField) {
                    val image = if (passwordVisibility.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(
                        onClick = {
                            passwordVisibility.value = passwordVisibility.value.not()
                        }
                    ) {
                        Icon(
                            imageVector = image,
                            contentDescription = null
                        )
                    }
                }
            },
            onValueChange = {
                onTextChanged(it)
                text.value = it
            },
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            keyboardActions = KeyboardActions(
                onAny = {
                    keyboardController?.hide()
                    onKeyboardAction?.invoke(text.value)
                }
            )
        )
        Spacer(modifier = Modifier.height(2.dp))
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun TextBox() {
    PrimaryTextBox(modifier = Modifier.fillMaxWidth(), value = "Hey there", placeholder = "Email", onTextChanged = {}, isError = true)
}
