package summary.news_line.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PromptTokenDetail {
    @JsonProperty("cached_tokens")
    private int cachedTokens;
    @JsonProperty("audit_tokens")
    private int auditTokens;
    @JsonProperty("audio_tokens")
    private int audioTokens;
}
