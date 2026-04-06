package summary.news_line.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usage {
    @JsonProperty("prompt_tokens")
    private int promptTokens;
    @JsonProperty("completion_tokens")
    private int completionTokens;
    @JsonProperty("total_tokens")
    private int totalTokens;
    @JsonProperty("prompt_tokens_details")
    private PromptTokenDetail promptTokenDetail;
    @JsonProperty("completion_tokens_details")
    private CompletionTokenDetail completionTokenDetail;
}
