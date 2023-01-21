package io.hoon.dck.infrastructure.persistence

import io.hoon.dck.domain.Data
import org.springframework.stereotype.Component
import java.util.concurrent.LinkedBlockingQueue

/**
 * 인 메모리 임시 저장소
 */
@Component
class InMemoryRepository {

    private val repository = LinkedBlockingQueue<Data>()

    fun save(data: Data) = repository.put(data)

    fun getAll(): List<Data> {
        val list = mutableListOf<Data>()
        repository.drainTo(list)
        return list
    }
}