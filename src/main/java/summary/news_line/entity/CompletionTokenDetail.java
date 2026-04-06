package summary.news_line.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompletionTokenDetail {
    @JsonProperty("reasoning_tokens")
    private int reasoningTokens;
    @JsonProperty("audio_tokens")
    private int audioTokens;
    @JsonProperty("accepted_prediction_tokens")
    private int acceptedPredictionTokens;
    @JsonProperty("rejected_prediction_tokens")
    private int rejectedPredictionTokens;
}
