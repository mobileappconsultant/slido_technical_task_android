package com.mobile.xenbank.androidapp.ui.composable

import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.usersdir.ui.composable.Field

@ExperimentalComposeUiApi
class FormState {
    var fields: List<Field> = listOf()

    fun validate(): Boolean {
        var valid = true
        for (field in fields) if (!field.validate()) {
            valid = false
            break
        }
        return valid
    }

    fun getData(): Map<String, String> = fields.associate { it.name to it.text }
}
