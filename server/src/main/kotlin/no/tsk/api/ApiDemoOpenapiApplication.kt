package no.tsk.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiDemoOpenapiApplication

fun main(args: Array<String>) {
    runApplication<ApiDemoOpenapiApplication>(*args)
}
