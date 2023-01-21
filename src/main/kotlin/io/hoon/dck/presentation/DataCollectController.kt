package io.hoon.dck.presentation

import io.hoon.dck.application.service.DataCollectService
import io.hoon.dck.presentation.dto.ApiResponse
import io.hoon.dck.presentation.dto.StringDataCollectReqDto
import io.hoon.dck.presentation.dto.StringDataCollectRespDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/data")
class DataCollectController(
    private val dataCollectService: DataCollectService
) {

    @PostMapping("/string")
    fun collectStringData(@RequestBody @Valid collectReqDto: StringDataCollectReqDto): ApiResponse =
        ApiResponse(
            body = StringDataCollectRespDto(
                prodType = collectReqDto.prodType,
                dataType = collectReqDto.dataType,
                uuid = dataCollectService.collectData(prodType = collectReqDto.prodType, dataType = collectReqDto.dataType, data = collectReqDto.data),
                successful = true
            )
        )
}