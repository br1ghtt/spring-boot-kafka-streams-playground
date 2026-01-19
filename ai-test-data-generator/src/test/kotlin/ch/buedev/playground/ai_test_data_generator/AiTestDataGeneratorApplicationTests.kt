package ch.buedev.playground.ai_test_data_generator

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.test.ShellAssertions
import org.springframework.shell.test.ShellTestClient
import org.springframework.shell.test.autoconfigure.ShellTest
import org.springframework.test.context.ContextConfiguration

@ShellTest
@ContextConfiguration(classes = [AiTestDataGeneratorApplication::class])
abstract class AiTestDataGeneratorApplicationTests
