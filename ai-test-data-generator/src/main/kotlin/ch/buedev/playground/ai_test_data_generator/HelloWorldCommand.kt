package ch.buedev.playground.ai_test_data_generator

import org.springframework.shell.core.command.annotation.Command
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger

@Component
class HelloWorldCommand {
    var counter = AtomicInteger(0)

    @Command(name = ["hello", "world"])
    fun helloWorld() = "hello world my friend ${counter.getAndIncrement()}"
}
