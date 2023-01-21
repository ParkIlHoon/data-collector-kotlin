package io.hoon.dck.infrastructure.event

import io.hoon.dck.application.manager.DataWriteManager
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * 데이터 수집 이벤트 핸들러
 */
@Service
class DataCollectEventHandler(
    private val dataWriteManager: DataWriteManager
) {

    @Async
    @EventListener
    fun dataCollectEventListener(dataCollectEvent: DataCollectEvent): Unit {
        dataWriteManager.delegateDataWriteTask()
    }
}