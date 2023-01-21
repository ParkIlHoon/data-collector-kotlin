package io.hoon.dck.domain

/**
 * 수집 문자열 데이터 엔티티
 *
 * @property data 수집 문자열 데이터
 */
class StringData (
    prodType: String,
    dataType: String,
    val data: String,
    clientIp: String,
    clientLocale: String,
    secretKey: String,
): Data(prodType = prodType, dataType = dataType, clientIp = clientIp, clientLocale = clientLocale, secretKey = secretKey)