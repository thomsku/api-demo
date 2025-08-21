package no.tsk.api

import no.tsk.model.Greeting
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid
import java.util.concurrent.atomic.AtomicLong
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import no.tsk.model.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler


@RestController
class GreetingController: GreetingHttpApi {
    private val counter = AtomicLong()

    @GetMapping("/greeting")
    override fun getGreeting(@Valid @RequestParam(value = "name", defaultValue = "World") name: String?): ResponseEntity<Greeting> {
        if (name == "Thomas") {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "The name 'Thomas' is not allowed")
        }
        return ResponseEntity.ok(Greeting(counter.incrementAndGet(), String.format(template, name)))
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