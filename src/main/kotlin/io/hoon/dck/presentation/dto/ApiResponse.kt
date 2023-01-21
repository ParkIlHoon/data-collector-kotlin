package io.hoon.dck.presentation.dto

data class ApiResponse(
    val resultCode: Int = 0,
    val resultMessage: String = "success",
    val body: Any?
)