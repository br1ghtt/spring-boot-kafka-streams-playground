package ch.buedev.playground.spring_boot_kafka_streams_playground

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.Properties

fun main() {
    val kafkaProperties =
        Properties().apply {
            setProperty("bootstrap.servers", "localhost:9092")
            put("client.id", "localhost")
            put("bootstrap.servers", "localhost:9092")
            put("security.protocol", "SASL_PLAINTEXT")
            put("sasl.mechanism", "SCRAM-SHA-512")
            put(
                "sasl.jaas.config",
                """org.apache.kafka.common.security.scram.ScramLoginModule required username="client" password="password";""",
            )
            put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer")
            put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        }

    val producer = KafkaProducer<Long, String>(kafkaProperties)

    producer.send(ProducerRecord("input-topic", 0L, "value")).get()
    producer.send(ProducerRecord("input-topic", 0L, null)).get()

    val byteArrayProducer =
        KafkaProducer<ByteArray, ByteArray>(
            kafkaProperties.apply {
                put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer")
                put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer")
            },
        )

    // should cause deserialization exception and channeled to dlq
    byteArrayProducer.send(ProducerRecord("input-topic", ByteArray(1), ByteArray(1))).get()
}
