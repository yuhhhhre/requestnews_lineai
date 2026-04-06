package summary.news_line.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定期的にニュースを配信するスケジューラーサービス。
 * @Scheduled(fixedRate)で1時間ごとにニュースを取得して配信する。
 */
@Service
public class SchedulerService {

    private final BroadcastMessageService broadcastMessageService;
    private final BbcNewsJP bbcNewsJP;
    private final BbcNewsEN bbcNewsEN;
    private final SummaryService summaryService;

    @Value("${line.user.id:}")
    private String lineUserId;

    public SchedulerService(BroadcastMessageService broadcastMessageService,
                            BbcNewsJP bbcNewsJP,
                            BbcNewsEN bbcNewsEN,
                            SummaryService summaryService) {
        this.broadcastMessageService = broadcastMessageService;
        this.bbcNewsJP = bbcNewsJP;
        this.bbcNewsEN = bbcNewsEN;
        this.summaryService = summaryService;
    }

    /**
     * 1時間ごと（fixedRate）に日本語ニュースを配信する。
     * fixedRate: 3600000ms = 1時間
     */
    @Scheduled(fixedRate = 3600000)
    public void sendNewsJPEveryHour() {
        try {
            if (lineUserId == null || lineUserId.isEmpty()) {
                System.out.println("[SchedulerService] lineUserId not configured, skipping");
                return;
            }

            System.out.println("[SchedulerService] Starting to send news (JP) for user: " + lineUserId);

            List<NewsArticle> articles = bbcNewsJP.fetchNewsJP();

            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("【定期ニュース配信 - 日本語】\n\n");

            for (NewsArticle article : articles) {
                String summary;
                try {
                    summary = summaryService.summary(article.getTitle(), article.getLink());
                } catch (Exception e) {
                    e.printStackTrace();
                    summary = "要約失敗";
                }
                messageBuilder
                        .append("■ ")
                        .append(article.getTitle())
                        .append("\n")
                        .append(article.getLink())
                        .append("\n")
                        .append(summary)
                        .append("\n\n");
            }

            if (messageBuilder.length() <= 30) {
                messageBuilder.append("ニュース取得失敗");
            }

            broadcastMessageService.sendMessage(lineUserId, messageBuilder.toString());
            System.out.println("[SchedulerService] News sent successfully");

        } catch (Exception e) {
            System.err.println("[SchedulerService] Error sending news");
            e.printStackTrace();
        }
    }

    /**
     * 1時間ごと（fixedRate）に英語ニュースを配信する。
     * 別の時間帯に実行されるように調整可能。
     */
    @Scheduled(fixedRate = 3600000, initialDelay = 1800000)
    public void sendNewsENEveryHour() {
        try {
            if (lineUserId == null || lineUserId.isEmpty()) {
                System.out.println("[SchedulerService] lineUserId not configured, skipping");
                return;
            }

            System.out.println("[SchedulerService] Starting to send news (EN) for user: " + lineUserId);

            List<NewsArticle> articles = bbcNewsEN.fetchNewsEN();

            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("【Scheduled News - English】\n\n");

            for (NewsArticle article : articles) {
                String summary;
                try {
                    summary = summaryService.summary(article.getTitle(), article.getLink());
                } catch (Exception e) {
                    e.printStackTrace();
                    summary = "Summary failed";
                }
                messageBuilder
                        .append("■ ")
                        .append(article.getTitle())
                        .append("\n")
                        .append(article.getLink())
                        .append("\n")
                        .append(summary)
                        .append("\n\n");
            }

            if (messageBuilder.length() <= 30) {
                messageBuilder.append("Failed to fetch news");
            }

            broadcastMessageService.sendMessage(lineUserId, messageBuilder.toString());
            System.out.println("[SchedulerService] News sent successfully");

        } catch (Exception e) {
            System.err.println("[SchedulerService] Error sending news");
            e.printStackTrace();
        }
    }
}
