package ch.buedev.playground.ai_test_data_generator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.shell.core.command.annotation.Command

@SpringBootApplication
class AiTestDataGeneratorApplication

fun main(args: Array<String>) {
    runApplication<AiTestDataGeneratorApplication>(*args)
}
