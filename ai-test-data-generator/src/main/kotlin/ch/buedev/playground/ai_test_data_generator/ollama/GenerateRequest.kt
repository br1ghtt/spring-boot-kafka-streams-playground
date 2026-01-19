package ch.buedev.playground.ai_test_data_generator.ollama

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class GenerateRequest(
    val model: String,
    val prompt: String? = null,
    val suffix: String? = null,
    val images: List<String>? = null,
    val format: Any? = null,
    val system: String? = null,
    val stream: Boolean = true,
    val think: Any? = null,
    val raw: Boolean? = null,
    @JsonProperty("keep_alive")
    val keepAlive: String? = null,
    val options: GenerateOptions? = null,
    val logprobs: Boolean? = null,
    @JsonProperty("top_logprobs")
    val topLogprobs: Int? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class GenerateOptions(
    val temperature: Double? = null,
    @JsonProperty("top_k")
    val topK: Int? = null,
    @JsonProperty("top_p")
    val topP: Double? = null,
    @JsonProperty("num_predict")
    val numPredict: Int? = null,
    @JsonProperty("num_ctx")
    val numCtx: Int? = null,
    val seed: Int? = null,
    val stop: List<String>? = null
)
