package no.tsk.api_demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiDemoApplication

fun main(args: Array<String>) {
	runApplication<ApiDemoApplication>(*args)
}
