package summary.news_line.service;

import org.springframework.stereotype.Service;

@Service
public class NewsCommandService {
    private static final String COMMAND_JP = "Japanese";
    private static final String COMMAND_EN = "English";

    public String analyzeCommand(String userText) {

        if ("日本語".equals(userText)) {
            return COMMAND_JP;
        }

        if ("英語".equals(userText)) {
            return COMMAND_EN;
        }
        return "unknown";
    }
}