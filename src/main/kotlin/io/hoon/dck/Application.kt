package io.hoon.dck

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DataCollectorKotlinApplication

fun main(args: Array<String>) {
    runApplication<DataCollectorKotlinApplication>(*args)
}
