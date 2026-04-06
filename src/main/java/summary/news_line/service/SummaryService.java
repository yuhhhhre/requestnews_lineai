package summary.news_line.service;

import org.springframework.stereotype.Service;

@Service
public class SummaryService {
    private final OpenaiService openaiService;

    public SummaryService(OpenaiService openaiService){
        this.openaiService = openaiService;
    }

    public String summary(String title, String link) {
        String prompt = """
                以下のニュースを日本語で2〜3行に要約してください。

                タイトル:
                %s

                記事URL:
                %s
                """.formatted(title, link);

        // OpenaiService 経由で ChatGPT を呼び出して要約を取得
        return openaiService.generateResponse(prompt);
    }

}
