package ch.buedev.playground.spring_boot_kafka_streams_playground

import org.apache.kafka.streams.kstream.KStream
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import java.util.function.Function

@SpringBootApplication
class SpringBootKafkaStreamsPlaygroundApplication {
    @Bean
    fun processingFunction(): Function<KStream<Long, String?>, KStream<Long, String>> =
        Function { inputStream ->
            inputStream
                .filterTombstones()
                .mapValues { value -> value!! }
        }

    private fun <T> T?.isTombstone(): Boolean = this == null

    private fun <K, V> KStream<K, V>.filterTombstones(): KStream<K, V> = this.filterNot { _, value -> value.isTombstone() }
}

fun main(args: Array<String>) {
    runApplication<SpringBootKafkaStreamsPlaygroundApplication>(*args)
}
