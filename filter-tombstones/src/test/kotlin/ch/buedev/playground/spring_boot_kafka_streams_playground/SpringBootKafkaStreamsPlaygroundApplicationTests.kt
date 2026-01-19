package ch.buedev.playground.spring_boot_kafka_streams_playground

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles

@EmbeddedKafka(
    brokerProperties = [
        "auto.create.topics.enable=false",
        "transaction.state.log.replication.factor=1",
        "transaction.state.log.min.isr=1",
    ],
)
@SpringBootTest
@ActiveProfiles("test")
class SpringBootKafkaStreamsPlaygroundApplicationTests {
    @Test
    fun contextLoads() {
    }
}
