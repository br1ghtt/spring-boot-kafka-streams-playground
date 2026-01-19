package ch.buedev.playground.ai_test_data_generator.ollama

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class GenerateResponse(
    val model: String,
    @JsonProperty("created_at")
    val createdAt: String,
    val response: String,
    val thinking: String? = null,
    val done: Boolean,
    @JsonProperty("done_reason")
    val doneReason: String? = null,
    @JsonProperty("total_duration")
    val totalDuration: Long? = null,
    @JsonProperty("load_duration")
    val loadDuration: Long? = null,
    @JsonProperty("prompt_eval_count")
    val promptEvalCount: Int? = null,
    @JsonProperty("prompt_eval_duration")
    val promptEvalDuration: Long? = null,
    @JsonProperty("eval_count")
    val evalCount: Int? = null,
    @JsonProperty("eval_duration")
    val evalDuration: Long? = null,
    val logprobs: List<LogProb>? = null,
)

data class LogProb(
    val token: String,
    val logprob: Double,
    @JsonProperty("top_logprobs")
    val topLogprobs: List<TopLogProb>? = null,
)

data class TopLogProb(
    val token: String,
    val logprob: Double,
)
