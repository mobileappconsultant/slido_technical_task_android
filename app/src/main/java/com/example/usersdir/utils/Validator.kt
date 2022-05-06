package com.example.usersdir.utils

private const val EMAIL_MESSAGE = "Invalid email address"
private const val REQUIRED_MESSAGE = "This field is required"

sealed interface Validator
open class Email(var message: String = EMAIL_MESSAGE) : Validator
open class Required(var message: String = REQUIRED_MESSAGE) : Validator
