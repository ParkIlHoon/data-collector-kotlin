package io.hoon.dck.application.service

import io.hoon.dck.domain.StringData
import io.hoon.dck.infrastructure.aop.PublishDataCollectEvent
import io.hoon.dck.infrastructure.persistence.InMemoryRepository
import io.hoon.dck.infrastructure.util.HttpRequestUtil
import io.hoon.dck.infrastructure.util.SecurityUtil
import org.springframework.stereotype.Service

/**
 * 데이터 수집 서비스
 */
@Service
class DataCollectService(
    private val inMemoryRepository: InMemoryRepository
) {

    /**
     * 데이터를 임시 저장합니다.
     *
     * @see io.hoon.dck.infrastructure.event.DataCollectEventHandler
     */
    @PublishDataCollectEvent
    fun collectData(prodType: String, dataType: String, data: String): String {
        val stringData = StringData(
            prodType = prodType,
            dataType = dataType,
            data = data,
            clientIp = HttpRequestUtil.getRemoteIp(),
            clientLocale = HttpRequestUtil.getLocale().isO3Country,
            secretKey = SecurityUtil.getSecretKey()
        )

        inMemoryRepository.save(stringData)

        return stringData.uuid
    }

    /**
     * 임시 저장된 데이터를 모두 반환합니다.
     */
    fun getData() = inMemoryRepository.getAll()
}