package com.sgp.utils

enum class ErrorsSGP(val message: String) {
    INCORRECT_EMAIL("You input incorrect email"),
    INCORRECT_PASSWORD("You input incorrect password"),
    UNKNOWN_USER("Unknown user"),
    USER_ALREADY_EXIST("User already exist")
}