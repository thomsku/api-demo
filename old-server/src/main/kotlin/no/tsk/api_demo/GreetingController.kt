package no.tsk.api_demo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController {
    private val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String?): Greeting {
        if (name == "Thomas") {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "The name 'Thomas' is not allowed")
        }
        return Greeting(counter.incrementAndGet(), String.format(template, name))
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(ex.reason ?: "Bad Request", ex.statusCode.value())
        return ResponseEntity.status(ex.statusCode).body(errorResponse)
    }

    companion object {
        private const val template = "Hello, %s!"
    }
}
