package no.tsk.api

import no.tsk.model.Greeting
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController: GreetingHttpApi {
    private val counter = AtomicLong()

    @GetMapping("/greeting")
    override fun getGreeting(@Valid @RequestParam(value = "name", defaultValue = "World") name: String?): ResponseEntity<Greeting> {
        return ResponseEntity.ok(Greeting(counter.incrementAndGet(), String.format(template, name)))
    }

    companion object {
        private const val template = "Hello, %s!"
    }
}