package io.hoon.dck.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * .txt 파일 생성자 설정
 */
@ConfigurationProperties(prefix = "writer.txt-file")
data class TxtFileWriterProperties (
    val rootPath: String = "/",
)