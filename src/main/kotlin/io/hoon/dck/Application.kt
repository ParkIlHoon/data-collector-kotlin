package io.hoon.dck

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAsync
@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan("io.hoon.dck.infrastructure.properties")
class DataCollectorKotlinApplication

fun main(args: Array<String>) {
    runApplication<DataCollectorKotlinApplication>(*args)
}
