package io.hoon.dck.application.writer

import io.hoon.dck.domain.Data

/**
 * 데이터 쓰기 인터페이스
 */
interface Writer {

    /**
     * 쓰기 작업이 가능한 상태인지를 반환합니다.
     */
    fun isWritable(): Boolean

    /**
     * 데이터 쓰기 작업을 수행합니다.
     */
    fun write(dataList: List<Data>)
}