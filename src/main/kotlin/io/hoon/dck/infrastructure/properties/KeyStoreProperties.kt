package io.hoon.dck.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * 키 저장소 설정
 */
@ConfigurationProperties(prefix = "keystore")
data class KeyStoreProperties (
    val keys: Map<String, String> = mapOf()
) {
    fun keyExists(key: String): Boolean = keys.containsValue(key)

    fun getKeyName(key: String): String = keys.filterValues { it == key }.entries.first().key
}