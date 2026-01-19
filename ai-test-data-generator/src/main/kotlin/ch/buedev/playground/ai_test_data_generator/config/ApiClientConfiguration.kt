package ch.buedev.playground.ai_test_data_generator.config

import ch.buedev.playground.ai_test_data_generator.ollama.OllamaApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer
import org.springframework.web.service.registry.HttpServiceGroup
import org.springframework.web.service.registry.HttpServiceGroupConfigurer.ClientCallback
import org.springframework.web.service.registry.HttpServiceGroupConfigurer.Groups
import org.springframework.web.service.registry.ImportHttpServices

/**
 *
 *
 * @author cedric.buehler@raiffeisen.ch
 * created on 19.01.2026
 */
@ImportHttpServices(group = "ollama", types = [OllamaApi::class])
@Configuration
class ApiClientConfiguration {
    @Bean
    fun groupConfigurer(): RestClientHttpServiceGroupConfigurer =
        RestClientHttpServiceGroupConfigurer { groups ->
            groups
                .filterByName("ollama")
                .forEachClient { _, builder ->
                    builder.baseUrl(
                        "http://localhost:11434",
                    )
                }
        }
}
