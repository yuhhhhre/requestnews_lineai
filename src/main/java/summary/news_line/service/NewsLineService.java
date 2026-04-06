package summary.news_line.service;

import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.messaging.model.TextMessage;
import com.linecorp.bot.webhook.model.MessageEvent;
import com.linecorp.bot.webhook.model.TextMessageContent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * テキストメッセージに対する返信ロジックをまとめるサービス。
 * <p>
 * LINE の {@code replyToken} は1イベントにつき1回しか使えないため、
 * テキスト用の {@code @EventMapping} は Controller 側に1つだけにし、
 * ここで「ニュース」コマンドかどうかを分岐してから返信する。
 */
@Service
public class NewsLineService {

    /** このメッセージ（前後空白除去後）と一致したときだけ BBC ニュースを取得する。 */
    private static final String NEWS_COMMAND_JA = "日本語";
    private static final String NEWS_COMMAND_EN = "英語";

    private final MessagingApiClient messagingApiClient;
    private final BbcNewsJP bbcNewsJP;
    private final BbcNewsEN bbcNewsEN;
    private final SummaryService summaryService;

    public NewsLineService(MessagingApiClient messagingApiClient,
                           BbcNewsJP bbcNewsJP,
                           BbcNewsEN bbcNewsEN,
                           SummaryService summaryService) {
        this.messagingApiClient = messagingApiClient;
        this.bbcNewsJP = bbcNewsJP;
        this.bbcNewsEN = bbcNewsEN;
        this.summaryService = summaryService;
    }

    /**
     * テキストメッセージ1件を処理する。
     * <ol>
     *   <li>テキスト以外は何もしない（スタンプ等は無視）。</li>
     *   <li>本文を {@code trim()} し、日本語の「{@value #NEWS_COMMAND_JA}」と完全一致なら BBC RSS から取得した本文を返す。</li>
     *   <li>それ以外は従来どおり、エコー応答する。</li>
     * </ol>
     */
    public void handleTextMessageEvent(MessageEvent event) {
        // デバッグ用: LINE から来たイベント状況をまず確認する
        System.out.println("[NewsLineService] replyToken=" + event.replyToken());
        System.out.println("[NewsLineService] messageClass=" +
                (event.message() == null ? "null" : event.message().getClass().getName()));

        if (!(event.message() instanceof TextMessageContent)) {
            messagingApiClient.replyMessage(new ReplyMessageRequest(
                    event.replyToken(),
                    List.of(new TextMessage("テキストメッセージのみ対応です")),
                    false));
            return;
        }

        TextMessageContent message = (TextMessageContent) event.message();
        String userText = message.text().trim();

        if (NEWS_COMMAND_JA.equals(userText)) {
            List<NewsArticle> articles = bbcNewsJP.fetchNewsJP();

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

            if (replyBuilder.isEmpty()) {
                replyBuilder.append("ニュース取得失敗");
            }

            messagingApiClient.replyMessage(new ReplyMessageRequest(
                    event.replyToken(),
                    List.of(new TextMessage(replyBuilder.toString())),
                    false));
            return;
        }

        if (NEWS_COMMAND_EN.equals(userText)) {
            List<NewsArticle> articles = bbcNewsEN.fetchNewsEN();

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

            if (replyBuilder.isEmpty()) {
                replyBuilder.append("ニュース取得失敗");
            }

            messagingApiClient.replyMessage(new ReplyMessageRequest(
                    event.replyToken(),
                    List.of(new TextMessage(replyBuilder.toString())),
                    false));
            return;
        }

        final String originalMessageText = " How may i Help you? " + message.text();
        messagingApiClient.replyMessage(new ReplyMessageRequest(
                event.replyToken(),
                List.of(new TextMessage(originalMessageText)),
                false));
    }
}
