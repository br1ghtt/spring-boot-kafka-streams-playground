package ch.buedev.playground.spring_boot_kafka_streams_playground

import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.LongSerializer
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.TestInputTopic
import org.apache.kafka.streams.TestOutputTopic
import org.apache.kafka.streams.TopologyTestDriver
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Properties
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KafkaStreamsTopologyTest {
    private lateinit var testDriver: TopologyTestDriver
    private lateinit var inputTopic: TestInputTopic<Long, String?>
    private lateinit var outputTopic: TestOutputTopic<Long, String>

    @BeforeEach
    fun setup() {
        val streamsBuilder = StreamsBuilder()

        val processingFunction = SpringBootKafkaStreamsPlaygroundApplication().processingFunction()
        val inputStream = streamsBuilder.stream<Long, String?>("input-topic")
        val outputStream = processingFunction.apply(inputStream)
        outputStream.to("output-topic")

        val topology = streamsBuilder.build()
        
        val props = Properties().apply {
            put(StreamsConfig.APPLICATION_ID_CONFIG, "test-topology")
            put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234")
            put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long()::class.java)
            put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String()::class.java)
        }
        
        testDriver = TopologyTestDriver(topology, props)

        inputTopic =
            testDriver.createInputTopic(
                "input-topic",
                LongSerializer(),
                StringSerializer(),
            )

        outputTopic =
            testDriver.createOutputTopic(
                "output-topic",
                LongDeserializer(),
                StringDeserializer(),
            )
    }

    @AfterEach
    fun tearDown() {
        testDriver.close()
    }

    @Test
    fun `should filter out tombstone messages`() {
        inputTopic.pipeInput(1L, "valid message")
        inputTopic.pipeInput(2L, null)
        inputTopic.pipeInput(3L, "another valid message")

        val outputRecords = outputTopic.readRecordsToList()

        assertEquals(2, outputRecords.size)
        assertEquals(1L, outputRecords[0].key())
        assertEquals("valid message", outputRecords[0].value())
        assertEquals(3L, outputRecords[1].key())
        assertEquals("another valid message", outputRecords[1].value())
    }

    @Test
    fun `should process all non-null messages`() {
        inputTopic.pipeInput(1L, "message 1")
        inputTopic.pipeInput(2L, "message 2")
        inputTopic.pipeInput(3L, "message 3")

        val outputRecords = outputTopic.readRecordsToList()

        assertEquals(3, outputRecords.size)
        assertEquals("message 1", outputRecords[0].value())
        assertEquals("message 2", outputRecords[1].value())
        assertEquals("message 3", outputRecords[2].value())
    }

    @Test
    fun `should filter all tombstones when only null messages are sent`() {
        inputTopic.pipeInput(1L, null)
        inputTopic.pipeInput(2L, null)
        inputTopic.pipeInput(3L, null)

        assertTrue(outputTopic.isEmpty)
    }

    @Test
    fun `should maintain message keys through processing`() {
        val testKey = 42L
        val testValue = "test message"

        inputTopic.pipeInput(testKey, testValue)

        val record = outputTopic.readRecord()

        assertEquals(testKey, record.key())
        assertEquals(testValue, record.value())
    }
}
