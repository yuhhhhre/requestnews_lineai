package summary.news_line.service;

import org.springframework.stereotype.Service;

import summary.news_line.logic.OpenaiAccess;

@Service
public class OpenaiService {
    private final OpenaiAccess openaiAccess;

    public OpenaiService(OpenaiAccess openaiAccess) {
        this.openaiAccess = openaiAccess;
    }
    public String generateResponse(String userMessage) {
        return openaiAccess.getOpenaiResponse(userMessage);
    }
}
