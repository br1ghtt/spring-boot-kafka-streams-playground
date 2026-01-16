package ch.buedev.playground.spring_boot_kafka_streams_playground.config

import ch.buedev.playground.spring_boot_kafka_streams_playground.PROFILE_LOCAL
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaConfig {
    @Bean
    fun outputTopics(): KafkaAdmin.NewTopics =
        KafkaAdmin.NewTopics(
            NewTopic("output-topic", 1, 1),
            NewTopic("input-dlq", 1, 1),
        )

    @Bean
    @Profile(PROFILE_LOCAL)
    fun inputTopics(): KafkaAdmin.NewTopics =
        KafkaAdmin.NewTopics(
            NewTopic("input-topic", 1, 1),
        )
}
