package summary.news_line.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MessageFormatter {

    private final SummaryService summaryService;

    public MessageFormatter(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    public String formatNews(List<NewsArticle> articles) {

        StringBuilder replyBuilder = new StringBuilder();

        for (NewsArticle article : articles) {
            String summary;
            try {
                summary = summaryService.summary(article.getTitle(), article.getLink());
            } catch (Exception e) {
                e.printStackTrace();
                summary = "要約失敗";
            }
            replyBuilder
                    .append("■ ")
                    .append(article.getTitle())
                    .append("\n")
                    .append(article.getLink())
                    .append("\n")
                    .append(summary)
                    .append("\n\n");
        }

        if (articles.isEmpty()) {
            return "ニュース取得失敗";
        }

        return replyBuilder.toString();
    }
}
