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

    private final MessagingApiClient messagingApiClient;
    private final MessageFormatter messageFormatter;
    private final LineReplyService lineReplyService;
    private final NewsFetchService newsFetchService;
    private final NewsCommandService newsCommandService;

    public NewsLineService(MessagingApiClient messagingApiClient,
                           MessageFormatter messageFormatter,
                           LineReplyService lineReplyService,
                           NewsFetchService newsFetchService,
                           NewsCommandService newsCommandService) {
        this.messagingApiClient = messagingApiClient;
        this.messageFormatter = messageFormatter;
        this.lineReplyService = lineReplyService;
        this.newsFetchService = newsFetchService;
        this.newsCommandService = newsCommandService;
    }

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
        String newsCommand = newsCommandService.analyzeCommand(userText);

        if ("Japanese".equals(newsCommand)) {
            List<NewsArticle> articles = newsFetchService.fetchJapaneseNews();
            
            String messageBuilder = messageFormatter.formatNews(articles);

            lineReplyService.reply(event.replyToken() ,messageBuilder);

            return;
        }

        if ("English".equals(newsCommand)) {
            List<NewsArticle> articles = newsFetchService.fetchEnglishNews();

            String messageBuilder = messageFormatter.formatNews(articles);
            
            lineReplyService.reply(event.replyToken() ,messageBuilder);

            return;
        }
    }
}
