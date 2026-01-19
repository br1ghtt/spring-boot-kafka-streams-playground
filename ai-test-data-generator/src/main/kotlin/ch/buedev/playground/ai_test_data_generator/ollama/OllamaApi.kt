package ch.buedev.playground.ai_test_data_generator.ollama

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange
interface OllamaApi {
    @GetExchange("/api/tags")
    fun tags(): String

    @PostExchange("/api/generate")
    fun generate(
        @RequestBody request: GenerateRequest,
    ): GenerateResponse
}
