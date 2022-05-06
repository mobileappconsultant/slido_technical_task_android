package com.example.usersdir.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.usersdir.ui.theme.Blue

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() }, shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Blue
        ),
        enabled = isLoading.not(),
        modifier = modifier
    ) {
        if (isLoading.not()) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = text,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        } else {
            CircularProgressIndicator()
        }
    }
}
