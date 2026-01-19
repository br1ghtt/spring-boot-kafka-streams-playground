package ch.buedev.playground.ai_test_data_generator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.test.ShellAssertions
import org.springframework.shell.test.ShellTestClient

/**
 *
 *
 * @author cedric.buehler@raiffeisen.ch
 * created on 19.01.2026
 */
class HelloWorldCommandTest : AiTestDataGeneratorApplicationTests() {
    @Test
    fun `hello world says hello world my friend and increments counter`(
        @Autowired client: ShellTestClient,
    ) {
        val command = "hello world"
        with(client.sendCommand(command)) {
            assertThat(this.lines.count()).isEqualTo(1)
            ShellAssertions.assertThat(this).containsText("hello world my friend 0")
        }
        with(client.sendCommand(command)) {
            assertThat(this.lines.count()).isEqualTo(1)
            ShellAssertions.assertThat(this).containsText("hello world my friend 1")
        }
    }
}
