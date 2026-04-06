package summary.news_line.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.internal.ws.RealWebSocket;

public class Choice {
    private int index;
    private RealWebSocket.Message message;
    private Object logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;
    private String refusal;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public RealWebSocket.Message getMessage() {
        return message;
    }

    public void setMessage(RealWebSocket.Message message) {
        this.message = message;
    }

    public Object getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(Object logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public String getRefusal() {
        return refusal;
    }

    public void setRefusal(String refusal) {
        this.refusal = refusal;
    }
}
