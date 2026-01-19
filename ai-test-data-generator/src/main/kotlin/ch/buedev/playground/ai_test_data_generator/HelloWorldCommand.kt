package ch.buedev.playground.ai_test_data_generator

import ch.buedev.playground.ai_test_data_generator.ollama.GenerateRequest
import ch.buedev.playground.ai_test_data_generator.ollama.OllamaApi
import org.springframework.shell.core.command.annotation.Argument
import org.springframework.shell.core.command.annotation.Command
import org.springframework.shell.core.command.annotation.Option
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger

@Component
class HelloWorldCommand(
    private val ollamaApi: OllamaApi,
) {
    @Command(name = ["ollama", "tags"], group = "ollama")
    fun ollamaTags() = ollamaApi.tags()

    @Command(name = ["ollama"], group = "ollama")
    fun ollamaGenerate() =
        ollamaApi
            .generate(
                GenerateRequest(model = "opencoder:1.5B", prompt = "hello world", stream = false),
            ).toString()
}
