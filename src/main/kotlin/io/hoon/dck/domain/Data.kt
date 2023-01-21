package io.hoon.dck.domain

import java.time.ZonedDateTime
import java.util.*

/**
 * 수집 데이터 엔티티
 *
 * @property uuid 데이터 고유 아이디
 * @property prodType 제품 타입
 * @property dataType 데이터 타입
 * @property collectedDateTime 수집 일시
 * @property clientIp 클라이언트 IP
 * @property clientLocale 클라이언트 Locale
 * @property secretKey 키
 */
sealed class Data(
    val prodType: String,
    val dataType: String,
    val clientIp: String,
    val clientLocale: String,
    val secretKey: String,
) {
    val uuid: String = UUID.randomUUID().toString()
    val collectedDateTime: ZonedDateTime = ZonedDateTime.now()
}