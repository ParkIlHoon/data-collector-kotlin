package io.hoon.dck.application.writer

import com.fasterxml.jackson.databind.ObjectMapper
import io.hoon.dck.domain.Data
import io.hoon.dck.domain.StringData
import io.hoon.dck.infrastructure.properties.KeyStoreProperties
import io.hoon.dck.infrastructure.properties.TxtFileWriterProperties
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * .txt 파일 쓰기
 */
@Component
class TxtFileWriter(
    private val keyStore: KeyStoreProperties,
    private val txtFileWriterProperties: TxtFileWriterProperties,
    private val objectMapper: ObjectMapper,
): Writer {

    private val separator = "\n"
    private val fileNameFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss")
    private val fileExt = ".txt"
    private val filePathMap = mutableMapOf<String, Path>()
    override fun isWritable() = filePathMap.isNotEmpty() && filePathMap.values.all { Files.exists(it) }

    override fun write(dataList: List<Data>) {
        dataList
            .filterIsInstance<StringData>()
            .groupBy { it.secretKey }
            .mapKeys { keyStore.getKeyName(it.key) }
            .filterKeys { filePathMap.containsKey(it) }
            .mapKeys { filePathMap[it.key] }
            .forEach {
                it.key?.let { path ->
                    val sb = StringBuilder()
                    it.value.forEach { data ->
                        sb.append(objectMapper.writeValueAsString(data)).append(separator)
                    }
                    Files.write(path, sb.toString().toByteArray(), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
                }
            }
    }

    /**
     * 데이터를 쓸 파일 생성 스케쥴링
     */
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    fun createFileJob() {
        createFile()
    }

    /**
     * 데이터를 쓸 파일을 생성합니다.
     */
    private fun createFile() {
        for (keyName in keyStore.keys.keys) {
            val path = Path.of(txtFileWriterProperties.rootPath, keyName, generateFileName())
            Files.createDirectories(path.parent)
            filePathMap[keyName] = Files.createFile(path)
        }
    }

    /**
     * 데이터를 쓸 파일명을 생성합니다.
     */
    private fun generateFileName() = ZonedDateTime.now().format(fileNameFormatter) + fileExt
}