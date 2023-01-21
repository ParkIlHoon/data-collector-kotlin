package io.hoon.dck.application.manager

import io.hoon.dck.application.service.DataCollectService
import io.hoon.dck.application.writer.Writer
import org.springframework.stereotype.Service

/**
 * 데이터 쓰기 매니저
 *
 * Writer 구현체에게 데이터 쓰기 작업을 위임합니다.
 */
@Service
class DataWriteManager(
    private val dataCollectService: DataCollectService,
    private val writers: List<Writer>,
) {

    fun delegateDataWriteTask() {
        val none = writers.none { it.isWritable() }
        if (none) return

        val data = dataCollectService.getData()
        writers.filter { it.isWritable() }.forEach { writer -> writer.write(data) }
    }
}