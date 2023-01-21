package io.hoon.dck.presentation.dto

import jakarta.validation.constraints.NotBlank

data class StringDataCollectReqDto(
    @NotBlank(message = "제품 타입은 필수에요")
    val prodType: String,
    @NotBlank(message = "데이터 타입은 필수에요")
    val dataType: String,
    @NotBlank(message = "데이터는 필수에요")
    val data: String,
)

data class StringDataCollectRespDto(
    val prodType: String,
    val dataType: String,
    val uuid: String?,
    val successful: Boolean = false,
)